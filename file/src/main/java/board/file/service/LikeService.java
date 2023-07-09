package board.file.service;

import board.file.dto.like.LikeDTO;

// Like Service Interface
public interface LikeService {

    // Toggle Like Service 
    int toggleLike(Long tno, String email);

    // Count Like Service
    Long countLike(Long tno);
}
