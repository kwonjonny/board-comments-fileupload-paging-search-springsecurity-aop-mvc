package board.file.mappers;

import org.apache.ibatis.annotations.Mapper;

import board.file.dto.like.LikeDTO;

// LikeMapper Interface
@Mapper
public interface LikeMapper {

    // Create Like
    int createLike(LikeDTO likeDTO); 

    // Delete Like
    int deleteLike(LikeDTO likeDTO); 

    // Count Like 
    int countLikes(Long tno);
}
