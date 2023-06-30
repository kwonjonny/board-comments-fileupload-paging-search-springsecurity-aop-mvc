package board.file.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;

// Reply Mapper Class 
@Mapper
public interface ReplyMapper {
    
    // Create Reply
    int createReply(ReplyCreateDTO replyCreateDTO);

    // Create Reply Child
    int createReplyChild(ReplyCreateDTO replyCreateDTO);

    // Update Gno 댓글 대댓글 구분 
    int updateReplyGno(Long gno);

    // Update Reply
    int updateReply(ReplyUpdateDTO replyUpdateDTO);

    // Delete Reply 
    int deleteReply(Long rno);

    // Read Reply
    ReplyDTO readReply(Long rno);

    // List Reply 
    List<ReplyDTO> listReply(@Param("page") PageRequestDTO pageRequestDTO, @Param("tno") Long tno);

    // Total Reply
    int totalReply(Long tno);

    // Increment Reply Count 
    int incrementReplyCount(Long tno);

    // Decrement Reply Count 
    int decrementReplyCount(Long tno);
}
