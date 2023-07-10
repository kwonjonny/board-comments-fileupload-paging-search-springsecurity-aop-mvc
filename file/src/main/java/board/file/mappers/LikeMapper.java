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

}
