package board.file.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeListDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.mappers.FileMapper;
import board.file.mappers.NoticeMapper;
import lombok.extern.log4j.Log4j2;

// NoticeService Test Class
@Log4j2
@SpringBootTest
public class NoticeServiceTests {

    // 의존성 주입
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private FileMapper fileMapper;

    private static final String TEST_TITLE = "JunitServiceTest";
    private static final String TEST_WRITER = "JunitServiceTest";
    private static final String TEST_CONTENT = "JunitServiceTest";
    private static final String TEST_FilENAMES = "Test.jpg";
    private static final Long TEST_NNO = 2L;

    // BeforeEach 사용을 위한 NoticeCreateDTO, NoticeUpdateDTO 정의
    private NoticeCreateDTO noticeCreateDTO;
    private NoticeUpdateDTO noticeUpdateDTO;
    private String uuid; // UUID를 저장할 변수

    // NoticeService Create Test Set Up
    // NoticeService Update Test Set Up
    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID().toString(); // UUID 생성

        noticeCreateDTO = NoticeCreateDTO.builder()
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .fileNames(Arrays.asList(uuid + "_" + TEST_FilENAMES)) // UUID와 파일 이름 결합하여 저장
                .build();

        noticeUpdateDTO = NoticeUpdateDTO.builder()
                .nno(TEST_NNO)
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .fileNames(Arrays.asList(uuid + "_" + TEST_FilENAMES)) // UUID와 파일 이름 결합하여 저장
                .build();
    }

    // Create Notice Service Test
    @Test
    @Transactional
    @DisplayName("Create Notice Service")
    public void createNoticeServiceTest() {
        // GIVEN
        log.info("=== Start Notice Service Create Test ===");
        // WHEN
        Long insertCount = noticeService.createNotice(noticeCreateDTO);
        AtomicInteger index = new AtomicInteger(0);

        List<String> fileNames = noticeCreateDTO.getFileNames();
        Long nno = noticeCreateDTO.getNno();
        List<Map<String, String>> list = fileNames.stream().map(str -> {
            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);
            return Map.of("uuid", uuid, "fileName", fileName, "nno", "" + nno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());
        // THEN
        NoticeDTO createdNoticeDTO = noticeService.readNotice(TEST_NNO);
        Assertions.assertNotNull(createdNoticeDTO, "createdNoticeDTO Should Be Successful And Not Null");
        log.info("=== End Notice Service Create Test ===");
    }

    // Delete Notice Service Test
    @Test
    @Transactional
    @DisplayName("Delete Notice Service")
    public void deleteNoticeServiceTest() {
        // GIVEN
        log.info("=== Start Notice Service Delete Test ===");
        // WHEN
        noticeService.deleteNotice(TEST_NNO);
        // THEN
        NoticeDTO deletedNotice = noticeService.readNotice(TEST_NNO);
        Assertions.assertNull(deletedNotice, "deletedNotice Should Be Null");
        log.info("=== End Notice Service Delete Test ===");
    }

    // Update Notice Service Test
    @Test
    @Transactional
    @DisplayName("Update Notice Serivce")
    public void updateNoticeServiceTest() {
        // GIVEN
        log.info("=== Start Notice Service Update Test ===");
        // WHEN
        Long result = noticeService.updateNotice(noticeUpdateDTO);
        fileMapper.deleteImageNotice(noticeUpdateDTO.getNno());
        AtomicInteger index = new AtomicInteger(0);

        List<String> fileNames = noticeUpdateDTO.getFileNames();
        Long nno = noticeUpdateDTO.getNno();
        List<Map<String, String>> list = fileNames.stream().map(str -> {
            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);
            return Map.of("uuid", uuid, "fileName", fileName, "nno", "" + nno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());
        fileMapper.updateImageNotice(list);
        // THEN
        NoticeDTO updatedNotice = noticeService.readNotice(TEST_NNO);
        Assertions.assertNotNull(updatedNotice, "updatedNotice Should Be Not Null");
        Assertions.assertEquals(TEST_TITLE, updatedNotice.getTitle(), "TITLE Sould Be Updated");
        log.info("=== End Notice Service Update Test ===");
    }

    // Read Notice Service Test
    @Test
    @Transactional
    @DisplayName("Read Notice Service")
    public void readNoticeServiceTest() {
        // GIVEN
        log.info("=== Start Notice Service Read Test ===");
        // WHEN
        NoticeDTO readNotice = noticeService.readNotice(TEST_NNO);
        // THEN
        log.info(readNotice);
        Assertions.assertNotNull(readNotice, "readNotice Should Be Not null");
        log.info("=== End Notice Service Read Test ===");
    }

    // List Notice Service Test
    @Test
    @Transactional
    @DisplayName("List Notice Service")
    public void listNoticeServiceTest() {
        // GIVEN
        log.info("=== Start Notice Service List Test ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .build();
        List<NoticeListDTO> list = noticeMapper.listNotice(pageRequestDTO);
        int total = noticeMapper.total(pageRequestDTO);
        PageResponseDTO<NoticeListDTO> pageResponseDTO = PageResponseDTO.<NoticeListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        // THEN
        log.info(pageResponseDTO.getList());
        log.info("=== End Notice Service List Test ===");
    }
}
