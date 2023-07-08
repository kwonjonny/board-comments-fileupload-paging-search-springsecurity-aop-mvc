package board.file.service;

import board.file.dto.like.LikeDTO;

// Like Service Interface
public interface LikeService {
    
    // Create Like Service 
    int createLike(LikeDTO likeDTO);

    // Delete Like Service
    int deleteLike(LikeDTO likeDTO);

    // Count Like Service
    int countLike(Long tno);
}
