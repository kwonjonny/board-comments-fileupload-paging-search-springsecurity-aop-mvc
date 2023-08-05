package board.file.service;

// Like Service Interface
public interface LikeService {

    // Toggle Like Service For Tno
    int toggleLike(Long tno, String email);

    // Count Like Service For Tno
    Long countLike(Long tno);

    // Toggle Like Service For Nno
    int toggleLikeNno(Long nno, String email);

    // Count Like Service For Nno
    Long countLikeNno(Long nno);

    // Not Found User
    void notFoundUser(String email);

    // Validation User Email
    void validationUserEmail(String email);
}
