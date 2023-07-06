package board.file.mappers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyMapperTets {

    @Autowired(required = false)
    private ReplyMapper replyMapper;

    private static final String TEST_REPLY = "Junit Reply Mapper Test";
    private static final String TEST_REPLYER = "Junit Replyer Mapper Test";

    private static final String TEST_REPLY_CHILD = "Junit Reply Child Mapper Test";
    private static final String TEST_RPELYER_CHILD = "Junit Replyer Child Mapper Test";

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

    // Create Reply Test
    @Test
    @Transactional
    @DisplayName("댓글 생성 테스트")
    public void createReplyMapperTest() {
        // GIVEN
        log.info("===== Start Create Reply Mapper Test =====");
        // WHEN
        int insertCount = replyMapper.createReply(replyCreateDTO);
        // THEN
        Assertions.assertEquals(1, insertCount, "Create Reply Should Be Successful");

        // Update rno
        Long rno = replyCreateDTO.getRno();
        replyMapper.updateReplyGno(rno);

        // Create Reply Check
        ReplyDTO replyDTO = replyMapper.readReply(rno);
        log.info(replyDTO);

        // tbl_board replyCnt Update
        replyMapper.incrementReplyCount(TEST_TNO);
        log.info("===== End Create Reply Mapper Test =====");
    }

    // Create Reply Child Test
    @Test
    @Transactional
    @DisplayName("대댓글 생성 테스트")
    public void createReplyChildMapperTest() {
        // GVIEN
        log.info("===== Start Create Reply Child Mapper Test =====");
        // WHEN
        int insertCount = replyMapper.createReplyChild(replyChildCreateDTO);
        // THEN
        Assertions.assertEquals(1, insertCount, "Create Reply Child Should Be Successful");
        log.info(replyChildCreateDTO.getGno());

        // Create Reply Child Check
        ReplyDTO replyDTO = replyMapper.readReply(TEST_RNO);
        log.info(replyDTO);

        // tbl_board replyCnt Update
        replyMapper.incrementReplyCount(TEST_TNO);

        log.info("===== End Create Reply Child Mapper Test =====");
    }

    // Delete Reply Test
    @Test
    @Transactional
    @DisplayName("댓글 삭제 테스트")
    public void delteReplyMapperTest() {
        // Given
        log.info("===== Start Delete Reply Mapper Test =====");
        // WHEN
        replyMapper.deleteReply(TEST_RNO);
        // THEN
        ReplyDTO replyDTO = replyMapper.readReply(TEST_RNO);
        Assertions.assertEquals("삭제된 댓글입니다.", replyDTO.getReply());
        replyMapper.decrementReplyCount(TEST_TNO);
        log.info("===== End Delete Reply Mapper Test =====");
    }

    // Update Reply Test
    @Test
    @Transactional
    @DisplayName("댓글 업데이트 테스트")
    public void updateReplyMapperTest() {
        // GIVEN
        log.info("===== Start Update Reply Mapper Test =====");
        // WHEN
        replyMapper.updateReply(replyUpdateDTO);
        // THEN
        ReplyDTO replyDTO = replyMapper.readReply(TEST_RNO);
        Assertions.assertNotNull(replyDTO, "replyDTO Should Be Not Null");
        Assertions.assertEquals("Junit Reply Mapper Test", replyDTO.getReply());
        log.info("===== End Update Reply Mapper Test =====");
    }

    // Update Reply Child Test
    @Test
    @Transactional
    @DisplayName("대댓글 업데이트 테스트")
    public void updateReplyChildMapperTest() {
        // GIVEN
        log.info("===== Start Update Reply Child Mapper Test =====");
        // WHEN
        replyMapper.updateReply(replyChildUpdateDTO);
        // THEN
        ReplyDTO replyDTO = replyMapper.readReply(TEST_RNO);
        Assertions.assertNotNull(replyDTO, "replyDTO Should Be Not Null");
        Assertions.assertEquals("Junit Reply Child Mapper Test", replyDTO.getReply());
        log.info("===== End Update Reply Child Mapper Test =====");
    }

    // List Reply Child Test
    @Test
    @Transactional
    @DisplayName("댓글 리스트 테스트")
    public void listReplyMapperTest() {
        // GIVEN
        log.info("===== Start List Reply Child Mapper Test =====");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<ReplyDTO> listReply = replyMapper.listReply(pageRequestDTO, TEST_TNO);
        // THEN
        log.info(listReply);
        log.info("===== End List Reply Child Mapper Test =====");
    }
}
