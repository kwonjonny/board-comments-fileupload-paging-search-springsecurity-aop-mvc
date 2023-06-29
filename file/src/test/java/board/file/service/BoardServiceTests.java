package board.file.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import lombok.extern.log4j.Log4j2;

// Board Service Test Class 
@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BoardServiceTests {
    
    // 의존성 주입 
    @Autowired
    private BoardService boardService;

    private static final String TEST_TITLE = "JunitServiceTest";
    private static final String TEST_WRITER = "JunitServiceTest";
    private static final String TEST_CONTENT = "JunitServiceTest";
    private static final Long TEST_TNO = 1L;

    // BeforeEach 사용을 위한 BoardCreateDTO , BoardUpdateDTO 정의 
    private BoardCreateDTO boardCreateDTO;
    private BoardUpdateDTO boardUpdateDTO;

    // BoardService Create Test Set Up
    // BoardService Update TEst Set Up
    @BeforeEach
    public void setUp() {
        boardCreateDTO = BoardCreateDTO.builder()
        .title(TEST_TITLE)
        .writer(TEST_WRITER)
        .content(TEST_CONTENT)
        .build();

        boardUpdateDTO = BoardUpdateDTO.builder()
        .tno(TEST_TNO)
        .title(TEST_TITLE)
        .writer(TEST_WRITER)
        .content(TEST_CONTENT)
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
        int insertCount = boardService.createBoard(boardCreateDTO);
        // THEN 
        Assertions.assertEquals(1, insertCount, "Create Board Service Should Successful");
        log.info("===== End Board Service Create Test =====");
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

    // Update Board Service Test 
    @Test
    @Transactional
    @DisplayName("Update Board Service")
    public void updateBoardServiceTest() {
        // GIVEN 
        log.info("===== Start Board Serivce Update Test =====");
        // WHEN 
        boardService.updateBoard(boardUpdateDTO);
        // THEN
        BoardDTO updatedBoardDTO = boardService.readBoard(TEST_TNO);
        Assertions.assertNotNull(updatedBoardDTO, "updatedBoardDTO Should Be Sucessful And Not Null");
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
        log.info("===== Start Board Service List Test =====");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .searchType("title")
            .keyword("TestTitle11")
            .build();
        PageResponseDTO<BoardListDTO> list = boardService.listboard(pageRequestDTO); 
        // THEN 
        log.info(list);
        // 가져온 게시물들이 검색 조건에 부합하는지 확인
        for (BoardListDTO board : list.getList()) {
        assertTrue(board.getTitle().contains("TestTitle11"), "Title should contain 'TestTitle11'");
        }
        log.info("===== End Board Service List Test =====");
    }
}
