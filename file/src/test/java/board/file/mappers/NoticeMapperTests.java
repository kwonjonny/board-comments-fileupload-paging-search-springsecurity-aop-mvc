package board.file.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import lombok.extern.log4j.Log4j2;

// NoticeMapper Test Class
@Log4j2
@SpringBootTest
public class NoticeMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    private static final String TEST_TITLE = "JunitTestTitleMapper";
    private static final String TEST_WRITER = "JunitTestWriterMapper";
    private static final String TEST_CONTENT = "JunitTestContentMapper";
    private static final Long TEST_NNO = 2L;

    // BeForeEach 사용을 위한 NoticeCreateDTO, NoticeUpdateDTO 정의
    private NoticeCreateDTO noticeCreateDTO;
    private NoticeUpdateDTO noticeUpdateDTO;

    // NoticeMapper Create Test Set Up
    // NoticeMapper Update Test Set Up
    @BeforeEach
    public void setUp() {
        noticeCreateDTO = NoticeCreateDTO.builder()
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .build();

        noticeUpdateDTO = NoticeUpdateDTO.builder()
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .build();
    }

    // List Notice Mapper Test 
    @Test
    @Transactional
    @DisplayName("공지사항 리스트 테스트")
    public void listNoticeMapperTest() {
        log.info("=== Start Notice List Mapper Test ===");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        // THEN 
        log.info(noticeMapper.listNotice(pageRequestDTO));
        log.info("=== End Notice List Mapper Test ===");
    }

    // Create Notice Mapper Test 
    @Test
    @Transactional
    @DisplayName("공지사항 생성 테스트")
    public void createNoticeMapperTest() {
        // GIVEN 
        log.info("=== Start Notice Create Mapper Test ===");
        // WHEN 
        noticeMapper.createNotice(noticeCreateDTO);
        // THEN 
        NoticeDTO readNotice = noticeMapper.readNotice(TEST_NNO);
        Assertions.assertNotNull(readNotice, "readNotice Should Be Not Null");
        log.info("=== End Notice Create Mapper Test ===");
    }

    // Delete Notice Mapper Test 
    @Test
    @Transactional
    @DisplayName("공지사항 삭제 테스트")
    public void deleteNoticeMapperTest() {
        // GIVEN 
        log.info("=== Start Notice Delete Mapper Test ===");
        // WHEN 
        noticeMapper.deleteNotice(TEST_NNO);
        // THEN 
        NoticeDTO readNotice = noticeMapper.readNotice(TEST_NNO);
        Assertions.assertNull(readNotice, "readNotice Should Be Null");
    }

    // Update Notice Mapper Test 
    @Test
    @Transactional
    @DisplayName("공지사항 업데이트 테스트")
    public void updateNoticeMapperTest() {
        // GIVEN 
        log.info("=== Start Notice Update Mapper Test ===");
        // WHEN 
        noticeMapper.updateNotice(noticeUpdateDTO);
        // THEN 
        NoticeDTO readNotice = noticeMapper.readNotice(TEST_NNO);
        Assertions.assertNotNull(readNotice, "readNotice Should Be Not Null");
        log.info("=== End Notie Update Mapper Test ===");
    }

    // Read Notice Mapper Test 
    @Test
    @Transactional
    @DisplayName("공지사항 상세 조회 테스트")
    public void readNoticeMapperTest() {
        // GIVEN
        log.info("=== Start Notice Read Mapper Test ===");
        // WHEN 
        NoticeDTO readNotice = noticeMapper.readNotice(TEST_NNO);
        log.info(readNotice);
        Assertions.assertNotNull(readNotice, "readNotice Should Be Not Null");
        log.info("=== End Notice Read Mapper Test ===");
    }
}
