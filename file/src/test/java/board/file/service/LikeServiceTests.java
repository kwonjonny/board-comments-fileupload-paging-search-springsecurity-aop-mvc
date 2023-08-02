package board.file.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.like.LikeDTO;
import board.file.mappers.LikeMapper;
import lombok.extern.log4j.Log4j2;

// Like Service Test Class 
@Log4j2
@SpringBootTest
public class LikeServiceTests {

    // 의존성 주입
    @Autowired
    private LikeService likeService;
    @Autowired
    private LikeMapper likeMapper;

    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_TNO = 1L;
    private static final Long TEST_NNO = 2L;

    // BeforeEach 사용을 위한 LikeDTO 정의
    private LikeDTO likeDTO;
    
    private LikeDTO likeDTOForNotice;

    // LikeDTO Create Mapper Set Up
    @BeforeEach
    public void setUp() {
        likeDTO = LikeDTO.builder()
                .email(TEST_EMAIL)
                .tno(TEST_TNO)
                .build();

        likeDTOForNotice = LikeDTO.builder()
                .email(TEST_EMAIL)
                .nno(TEST_NNO)
                .build();
    }

    // Count Like Service Test For Notice 
    @Test
    @Transactional
    @DisplayName("라이크 조회 공지사항 서비스 테스트")
    public void countServiceTestForNotice() {
        // GIVEN 
        log.info("=== Start LikeCount For Notice Servic Test ===");
        // WHEN 
        Long count = likeService.countLikeNno(TEST_NNO);
        // THEN 
        Assertions.assertNotNull(count);
        log.info("=== End LikCount For Notice Service Test ===");
    }

    // Toggle Like Service Test For Notice 
    @Test
    @Transactional
    @DisplayName("라이크 생성, 삭제, 조회 공지사항 서비스 테스트")
    public void toggleLikeServiceTestForNotice() {
        // GIVEN 
        log.info("=== Start Toggle Like Service For Notice ===");
        // WHEN 
        LikeDTO checkingLike = likeMapper.checkLikeByMemberAndPostNno(TEST_NNO, TEST_EMAIL);
        if(checkingLike == null) {
            // WHEN 
            int result = likeMapper.createLikeNno(likeDTOForNotice);
            // THEN 
            Assertions.assertNotEquals(0, result, "Create Like Should Return A non-zero Value");
        } else {
            // WHEN 
            int result = likeMapper.deleteLikeNno(likeDTOForNotice);
            // THEN 
            Assertions.assertNotEquals(0, result, "Delete Like Should Return A non-zero Value");
            LikeDTO deletedLike = likeMapper.checkLikeByMemberAndPostNno(TEST_NNO, TEST_EMAIL);
            Assertions.assertNull(deletedLike, "Deleted Like Should Be Null");
        }
        log.info("=== End Toggle Like Service For Notice ===");
    }

    // Count Like Service Test
    @Test
    @Transactional
    public void countServiceTest() {
        // GIVEN 
        log.info("=== Start LikeCount Service Test ===");
        // WHEN 
        Long count = likeService.countLike(TEST_TNO);
        // THEN 
        Assertions.assertNotNull(count);
        log.info("=== End LikeCount Service Test ===");
    }

    // Toggle Like Service Test
    @Test
    @Transactional
    public void toggleLikeServiceTest() {
        // GIVEN 
        log.info("=== Start Toggle Like Service Test ===");
        // WHEN 
        LikeDTO exisingLike = likeMapper.checkLikeByMemberAndPost(TEST_TNO, TEST_EMAIL);
        if(exisingLike == null) {
            // WHEN 
            int result = likeMapper.createLike(likeDTO);
            // THEN 
            Assertions.assertNotEquals(0, result, "Create Like Should Return a non-zero Value");
        } else {
            // WHEN 
            int result = likeMapper.deleteLike(likeDTO);
            // THEN 
            Assertions.assertNotEquals(0, result, "Delete Like Shoukd Return A non-zero Value");
            LikeDTO deletedLike = likeMapper.checkLikeByMemberAndPost(TEST_TNO, TEST_EMAIL);
            Assertions.assertNull(deletedLike, "Deleted Like should be null");
        }
        log.info("=== End Toggle Like Service Test ===");
    }
}
