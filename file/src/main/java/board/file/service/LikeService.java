package board.file.service;

// Like Service Interface
public interface LikeService {

    // Toggle Like Service 
    int toggleLike(Long tno, String email);

    // Count Like Service
    Long countLike(Long tno);
}
