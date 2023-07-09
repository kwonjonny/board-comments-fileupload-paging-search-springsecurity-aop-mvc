package board.file.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.like.LikeDTO;
import lombok.extern.log4j.Log4j2;

// Like Mapper Test Class 
@Log4j2
@SpringBootTest
public class LikeMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private LikeMapper likeMapper;

    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_TNO = 1L;

    // BeforeEach 사용을 위한 LikeDTO 정의
    private LikeDTO likeDTO;

    // LikeDTO Create Mapper Set Up
    @BeforeEach
    public void setUp() {
        likeDTO = LikeDTO.builder()
                .email(TEST_EMAIL)
                .tno(TEST_TNO)
                .build();
    }

    @Test
    @Transactional
    @DisplayName("좋아요 생성 테스트")
    public void createLikeMapperTest() {
        // GIVEN
        log.info("=== Start Create Like Mapper Test ===");
        // WHEN
        likeMapper.createLike(likeDTO);
        // THEN
        Long count = likeMapper.countLikes(TEST_TNO);
        Assertions.assertNotNull(count);
        log.info("=== End Create Like Mapper Test ===");
    }

    @Test
    @Transactional
    @DisplayName("좋아요 삭제 테스트")
    public void deleteLikeMapperTest() {
        // GIVEN
        log.info("=== Start Delete Like Mapper Test ===");
        // WHEN
        likeMapper.deleteLike(likeDTO);
        // THEN
        Long count = likeMapper.countLikes(TEST_TNO);
        Assertions.assertNull(count, "TEST_TNO Should Be Null");
        log.info("=== End Delete Like Mapper Test ===");
    }

    @Test
    @Transactional
    @DisplayName("좋아요 조회 테스트")
    public void countLikeMapperTest() {
        // GIVEN
        log.info("=== Start Count Like Mapper Test ===");
        Long count = likeMapper.countLikes(TEST_TNO);
        log.info(count);
        log.info("=== End Count Like Mapper Test ===");
    }

}
