package board.file.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.like.LikeDTO;
import board.file.exception.InvalidEmailException;
import board.file.exception.UserNotFoundException;
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
        validationUserEmail(email); // Validation Email
        notFoundUser(email); // Check Email
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

    // Count Like Nno ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public Long countLikeNno(Long nno) {
        log.info("LikeCount For Nno LikeServiceImpl Is Running");
        return likeMapper.countLikesNno(nno);
    }

    // Toggle Like Nno ServiceImpl
    @Override
    @Transactional
    public int toggleLikeNno(Long nno, String email) {
        log.info("ToggleLike Nno LikeServiceImpl Is Running");
        validationUserEmail(email); // Validation Email 
        notFoundUser(email);    // Check Email 
        LikeDTO likeDTO = LikeDTO.builder()
                .nno(nno)
                .email(email)
                .build();
        LikeDTO existingLike = likeMapper.checkLikeByMemberAndPostNno(nno, email);
        if (existingLike == null) {
            return likeMapper.createLikeNno(likeDTO);
        } else {
            return likeMapper.deleteLikeNno(likeDTO);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void notFoundUser(String email) {
        log.info("Already User Email LikeServiceImpl Is Running");
        if (likeMapper.checkMemberEmail(email) == 1) {
            throw new UserNotFoundException("이미 가입된 사용자 입니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void validationUserEmail(String email) {
        log.info("Validation User Email LikeServiceImpl Is Running");
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("이메일 형식이 올바르지 않습니다: " + email);
        }
    }
}
