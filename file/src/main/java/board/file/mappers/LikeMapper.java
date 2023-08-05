package board.file.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.file.dto.like.LikeDTO;

// LikeMapper Interface
@Mapper
public interface LikeMapper {

    // Create Like
    int createLike(LikeDTO likeDTO); 

    // Delete Like
    int deleteLike(LikeDTO likeDTO); 

    // Count Like 
    Long countLikes(Long tno);

    // Check Like Member    
    LikeDTO checkLikeByMemberAndPost(@Param("tno") Long tno, @Param("email") String email);

    // Count Like For Nno
    Long countLikesNno(Long nno);

    // Check Like Member Nno
    LikeDTO checkLikeByMemberAndPostNno(@Param("nno") Long nno, @Param("email") String email);

    // Create Like For Nno
    int createLikeNno(LikeDTO likeDTO);

    // Delete Like For Nno
    int deleteLikeNno(LikeDTO likeDTO);

    // Check Member Email
    int checkMemberEmail(String email); 
}
