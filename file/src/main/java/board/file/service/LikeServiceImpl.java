package board.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.like.LikeDTO;
import board.file.mappers.LikeMapper;
import lombok.extern.log4j.Log4j2;

// LikeServiceImpl Class 
@Log4j2
@Service
public class LikeServiceImpl implements LikeService {

    // 의존성 주입
    private final LikeMapper likeMapper;

    // Autowired 명시적 표시
    @Autowired
    public LikeServiceImpl(LikeMapper likeMapper) {
        log.info("Constructor Called, Like Mapper Injected.");
        this.likeMapper = likeMapper;
    }

    // Count Like ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public Long countLike(Long tno) {
        log.info("LikeCount LikeServiceImpl Is Running");
        return likeMapper.countLikes(tno);
    }

    // Toggle Like ServiceImpl
    @Override
    @Transactional
    public int toggleLike(Long tno, String email) {
        log.info("ToggleLike LikeServiceImpl Is Running");
        log.info(tno + email+ "티엔오 이메일");
        LikeDTO likeDTO = LikeDTO.builder()
                .email(email)
                .tno(tno)
                .build();
        LikeDTO existingLike = likeMapper.checkLikeByMemberAndPost(tno, email);
        if (existingLike == null) {
            return likeMapper.createLike(likeDTO);
        } else {
            return likeMapper.deleteLike(likeDTO);
        }
    }
}
