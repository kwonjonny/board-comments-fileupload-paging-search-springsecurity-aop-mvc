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

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.mappers.BoardMapper;
import board.file.mappers.FileMapper;
import lombok.extern.log4j.Log4j2;

// Board Service Test Class 
@Log4j2
@SpringBootTest
public class BoardServiceTests {

    // 의존성 주입
    @Autowired
    private BoardService boardService;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private BoardMapper boardMapper;

    private static final String TEST_TITLE = "JunitServiceTest";
    private static final String TEST_WRITER = "JunitServiceTest";
    private static final String TEST_CONTENT = "JunitServiceTest";
    private static final String TEST_FilENAMES = "Test.jpg";
    private static final Long TEST_TNO = 2556001L;

    // BeforeEach 사용을 위한 BoardCreateDTO , BoardUpdateDTO 정의
    private BoardCreateDTO boardCreateDTO;
    private BoardUpdateDTO boardUpdateDTO;
    private String uuid; // UUID를 저장할 변수

    // BoardService Create Test Set Up
    // BoardService Update TEst Set Up
    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID().toString(); // UUID 생성

        boardCreateDTO = BoardCreateDTO.builder()
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .fileNames(Arrays.asList(uuid + "_" + TEST_FilENAMES)) // UUID와 파일 이름 결합하여 저장
                .build();

        boardUpdateDTO = BoardUpdateDTO.builder()
                .tno(TEST_TNO)
                .title(TEST_TITLE)
                .writer(TEST_WRITER)
                .content(TEST_CONTENT)
                .fileNames(Arrays.asList(uuid + "_" + TEST_FilENAMES)) // UUID와 파일 이름 결합하여 저장
                .build();
    }

    // Create Board Service Test
    @Test
    @Transactional
    @DisplayName("Create Board Service")
    public void createBoardServiceTest() {
        // GIVEN
        log.info("===== Start Board Service Create Test =====");
        // WHEN
        Long insertCount = boardService.createBoard(boardCreateDTO);
        AtomicInteger index = new AtomicInteger(0);

        List<String> fileNames = boardCreateDTO.getFileNames();
        Long tno = boardCreateDTO.getTno();
        List<Map<String, String>> list = fileNames.stream().map(str -> {
            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);
            return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());
        // THEN
        BoardDTO createdBoardDTO = boardService.readBoard(TEST_TNO);
        Assertions.assertNotNull(createdBoardDTO, "createdBoardDTO Should Be Successful And Not Null");
        log.info("===== End Board Service Create Test =====");
    }

    // Update Board Service Test
    @Test
    @Transactional
    @DisplayName("Update Board Service")
    public void updateBoardServiceTest() {
        // GIVEN
        log.info("===== Start Board Serivce Update Test =====");
        // WHEN
        boardService.updateBoard(boardUpdateDTO);
        fileMapper.deleteImage(boardUpdateDTO.getTno());
        AtomicInteger index = new AtomicInteger(0);

        List<String> fileNames = boardUpdateDTO.getFileNames();
        Long tno = boardUpdateDTO.getTno();
        List<Map<String, String>> list = fileNames.stream().map(str -> {
            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);
            return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());

        fileMapper.createImage(list);

        // THEN
        BoardDTO updatedBoardDTO = boardService.readBoard(TEST_TNO);
        Assertions.assertNotNull(updatedBoardDTO, "updatedBoardDTO Should Be Sucessful And Not Null");
        Assertions.assertEquals(TEST_TITLE, boardUpdateDTO.getTitle(), "TITLE Should Be Updated");
        log.info("===== End Board Service Update Test =====");
    }

    // Read Board Service Test
    @Test
    @Transactional
    @DisplayName("Read Board Service")
    public void readBoardServiceTest() {
        // GIVEN
        log.info("===== Start Board Service Read Test =====");
        // WHEN
        BoardDTO boardDTO = boardService.readBoard(TEST_TNO);
        // THEN
        log.info(boardDTO);
        Assertions.assertNotNull(boardDTO, "boardDTO Should Be Not Null");
        log.info("===== End Board Service Read Test =====");
    }

    // List Board Service Test
    @Test
    @Transactional
    @DisplayName("List Board Service")
    public void listBoardServiceTest() {
        // GIVEN
        log.info("===== Start Board Service List Test =====");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .build();
        List<BoardListDTO> list = boardMapper.listBoard(pageRequestDTO);
        int total = boardMapper.total(pageRequestDTO);
        PageResponseDTO<BoardListDTO> pageResponseDTO = PageResponseDTO.<BoardListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        // THEN
        log.info(pageResponseDTO.getList());
        log.info("===== End Board Service List Test =====");
    }

    // Delete Board Service Test
    @Test
    @Transactional
    @DisplayName("Delete Board Service")
    public void deleteBoardSerivceTest() {
        // GIVEN
        log.info("===== Start Board Service Delete Test =====");
        // WHEN
        boardService.deleteBoard(TEST_TNO);
        // THEN
        BoardDTO deletedBoardDTO = boardService.readBoard(TEST_TNO);
        Assertions.assertNull(deletedBoardDTO, "deleteBoardDTO Should Be null");
        log.info("===== End Board Service Delete Test =====");
    }
}
