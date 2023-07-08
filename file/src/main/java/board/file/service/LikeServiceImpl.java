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

    // Create Like ServiceImpl
    @Override
    @Transactional
    public int createLike(LikeDTO likeDTO) {
        log.info("CreateLike LikeServiceImpl Is Running");
        return likeMapper.createLike(likeDTO);
    }

    // Delete Like ServiceImpl
    @Override
    @Transactional
    public int deleteLike(LikeDTO likeDTO) {
        log.info("DeleteLike LikeServiceImpl Is Running");
        return likeMapper.deleteLike(likeDTO);
    }

    // Count Like ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public int countLike(Long tno) {
        log.info("LikeCount LikeServiceImpl Is Running");
        return likeMapper.countLikes(tno);
    }
}
