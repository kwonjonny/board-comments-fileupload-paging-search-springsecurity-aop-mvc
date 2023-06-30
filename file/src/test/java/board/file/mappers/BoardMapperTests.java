package board.file.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import lombok.extern.log4j.Log4j2;

// BoardMapper Test Class
@Log4j2
@SpringBootTest
public class BoardMapperTests {

    // 의존성 주입 
    @Autowired(required = false)
    private BoardMapper boardMapper;

    private static final String TEST_TITLE = "JunitTestTitleMapper";
    private static final String TEST_WRITER = "JunitTestWriterMapper";
    private static final String TEST_CONTENT = "JunitTestContentMapper";
    private static final Long TEST_TNO = 1L;

    // BoeforeEach 사용을 위한 BoardCreateDTO, BoardUpdateDTO 정의 
    private BoardCreateDTO boardCreateDTO;
    private BoardUpdateDTO boardUpdateDTO;

    // BoardMapper Create Test Set Up
    // BoardMapper Update TEst Set Up
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

    // List Board 
    @Test
    @DisplayName("List Board Test Mapper")
    public void listBoardTest() {
        log.info("===== Start Board List Test Mapper =====");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        // THEN 
        log.info(boardMapper.listBoard(pageRequestDTO));
        log.info("===== End Board List Test Mapper =====");
    }

    // Create Board 
    @Test
    @Transactional
    @DisplayName("Create Board Test Mapper")
    public void createBoardTest() {
        // GIVEN 
        log.info("===== Start Board Create Test Mapper =====");
        // WHEN 
        int insertCount = boardMapper.createBoard(boardCreateDTO);
        // THEN 
        Assertions.assertEquals(1 , insertCount, "Create Should Be Successful");
        log.info("===== End Board Create Test Mapper =====");
    }

    // Delete Board 
    @Test
    @Transactional
    @DisplayName("Delete Board Test Mapper")
    public void deleteBoardTest() {
        // GIVEN
        log.info("===== Start Board Delete Test Mapper =====");
        // WHEN 
        boardMapper.deleteBoard(TEST_TNO);
        // THEN 
        BoardDTO deletedBoardDTO = boardMapper.readBoard(TEST_TNO);
        log.info(deletedBoardDTO);
        Assertions.assertNull(deletedBoardDTO, "deletedBoardDTO Should Be Null");
        log.info("===== End Board Delete Test Mapper =====");
    }

    // Read Board
    @Test
    @Transactional
    @DisplayName("Read Board Test Mapper")
    public void readBoardTest() {
        // GIVEN 
        log.info("===== Start Read Board Test Mapper =====");
        // WHEN 
        BoardDTO boardDTO = boardMapper.readBoard(TEST_TNO);
        // THEN 
        log.info(boardDTO);
        Assertions.assertNotNull(boardDTO, "boardDTO Should Be Not Null");
        log.info("===== End Board Read Test Mapper =====");
    }

    // Update Board
    @Test
    @Transactional
    @DisplayName("Update Board Test Mapper")
    public void updateBoardTest() {
        // GIVEN
        log.info("===== Start Update Board Test Mapper =====");
        // WHEN 
        boardMapper.updateBoard(boardUpdateDTO);
        // THEN 
        BoardDTO updatedBoardDTO = boardMapper.readBoard(TEST_TNO);
        Assertions.assertNotNull(updatedBoardDTO, "updatedBoardDTO Should Be Successful");
        log.info("===== End Board Update Test Mapper =====");
    }
}
