package board.file.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;
import board.file.mappers.ReplyMapper;
import lombok.extern.log4j.Log4j2;

// Reply Service Test Class 
@Log4j2
@SpringBootTest
public class ReplyServiceTests {

    // 의존성 주입
    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyMapper replyMapper;

    private static final String TEST_REPLY = "Junit Reply Service Test";
    private static final String TEST_REPLYER = "Junit Replyer Service Test";

    private static final String TEST_REPLY_CHILD = "Junit Reply Child Service Test";
    private static final String TEST_RPELYER_CHILD = "Junit Replyer Child Service Test";

    private static final Long TEST_RNO = 23L;
    private static final Long TEST_TNO = 1L;
    private static final Long TEST_GNO = 23L;

    // BeforeEach 사용을 위한 ReplyCreateDTO , ReplyUpdateDTO 정의
    private ReplyCreateDTO replyCreateDTO;
    private ReplyCreateDTO replyChildCreateDTO;

    private ReplyUpdateDTO replyUpdateDTO;
    private ReplyUpdateDTO replyChildUpdateDTO;

    // Reply Create Serivce Child Set Up
    // Reply Create Serivce Set Up
    // Reply Update Service Child Set Up
    // Reply Update Service Set Up
    @BeforeEach
    public void setUp() {
        replyCreateDTO = ReplyCreateDTO.builder()
                .tno(TEST_TNO)
                .reply(TEST_REPLY)
                .replyer(TEST_REPLYER)
                .build();

        replyChildCreateDTO = ReplyCreateDTO.builder()
                .tno(TEST_TNO)
                .reply(TEST_REPLY_CHILD)
                .replyer(TEST_RPELYER_CHILD)
                .gno(TEST_GNO)
                .build();

        replyUpdateDTO = ReplyUpdateDTO.builder()
                .tno(TEST_TNO)
                .reply(TEST_REPLY)
                .replyer(TEST_REPLYER)
                .rno(TEST_RNO)
                .build();

        replyChildUpdateDTO = ReplyUpdateDTO.builder()
                .tno(TEST_TNO)
                .reply(TEST_REPLY_CHILD)
                .replyer(TEST_RPELYER_CHILD)
                .rno(TEST_RNO)
                .gno(TEST_GNO)
                .build();
    }

    // Create Reply Service Test
    @Test
    @Transactional
    @DisplayName("댓글 생성 서비스 테스트")
    public void createReplyServieTest() {
        // GIVEN
        log.info("=== Start Reply Service Create Test ===");
        // WHEN
        Long insertcount = replyService.createReply(replyCreateDTO);
        // THEN
        ReplyDTO replyDTO = replyService.readReply(replyCreateDTO.getRno());
        Assertions.assertNotNull(replyDTO, "Reply Service Should Not Null");
        log.info("=== End Reply Service Create Test ===");
    }

    // Create Reply Child Service Test
    @Test
    @Transactional
    @DisplayName("대댓글 생성 서비스 테스트")
    public void createReplyChildServiceTest() {
        // GIVEN
        log.info("=== Start Reply Child Service Create Test ===");
        // WHEN
        Long insertCount = replyService.createReply(replyChildCreateDTO);
        // THEN
        ReplyDTO replyDTO = replyService.readReply(replyChildCreateDTO.getRno());
        Assertions.assertNotNull(replyDTO, "Reply Child Service Should Be Not Null");
        log.info("=== End Reply Child Service Create Test ===");
    }

    // Delete Reply Service Test
    @Test
    @Transactional
    @DisplayName("댓글 삭제 서비스 테스트")
    public void deleteReplyServiceTest() {
        // GIVEN
        log.info("=== Start Reply Service Delete TEst ===");
        // WHEN
        replyService.deleteReply(TEST_RNO);
        // THEN
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        log.info(TEST_REPLY);
        assertEquals("삭제된 댓글입니다.", replyDTO.getReply());
        log.info("=== End Reply Service Delete Test ===");
    }

    // Read Reply Service Test
    @Test
    @Transactional
    @DisplayName("댓글 조회 서비스 테스트")
    public void readReplyServiceTest() {
        // GIVEN
        log.info("=== Start Reply Service Read Test ===");
        // WHEN
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        // THEN
        log.info(replyDTO);
        Assertions.assertNotNull(replyDTO);
        log.info("=== End Reply Service Read Test ===");
    }

    // Update Reply Service Test
    @Test
    @Transactional
    @DisplayName("댓글 업데이트 테스트")
    public void updateReplyServiceTest() {
        // GIVEN
        log.info("=== Start Reply Serivce Update Test ===");
        // WHEN
        replyService.updateReply(replyUpdateDTO);
        // THEN
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        log.info(replyDTO);
        Assertions.assertEquals("Junit Reply Service Test", replyDTO.getReply());
        Assertions.assertNotNull(replyDTO);
        log.info("=== End Reply Service Update Test ===");
    }

    // Update Reply Child Service Test
    @Test
    @Transactional
    @DisplayName("대댓글 업데이트 서비스 테스트")
    public void updateReplyChildServiceTest() {
        // GIVEN
        log.info("=== Start Reply Child Service Update Test ===");
        // WHEN
        replyService.updateReply(replyChildUpdateDTO);
        // THEN
        ReplyDTO replyDTO = replyService.readReply(TEST_RNO);
        log.info(replyDTO);
        Assertions.assertEquals("Junit Reply Child Service Test", replyDTO.getReply());
        Assertions.assertNotNull(replyDTO);
        log.info("=== End Reply Child Service Update Test ===");
    }

    // List Reply Service Test
    @Test
    @Transactional
    @DisplayName("댓글 리스트 서비스 테스트")
    public void listReplyServiceTest() {
        // GIVEN
        log.info("=== Start Reply Service List Test ===");
        Long tno = TEST_TNO;
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<ReplyDTO> list = replyMapper.listReply(pageRequestDTO, tno);
        int total = replyMapper.totalReply(tno);
        PageResponseDTO<ReplyDTO> pageResponseDTO = PageResponseDTO.<ReplyDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        // THEN
        log.info(pageResponseDTO.getList());
        log.info("=== End Reply Service List Test ===");
    }
}
