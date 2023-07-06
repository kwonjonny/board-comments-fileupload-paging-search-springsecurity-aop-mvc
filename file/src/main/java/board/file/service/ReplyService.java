package board.file.service;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;

// Reply Service interface 
public interface ReplyService {

    // Create Reply Service
    Long createReply(ReplyCreateDTO replyCreateDTO);

    // Delete Reply Service
    int deleteReply(Long rno);

    // Update Reply Service
    int updateReply(ReplyUpdateDTO replyUpdateDTO);

    // Read Reply Service
    ReplyDTO readReply(Long rno);

    // List Reply Service
    PageResponseDTO<ReplyDTO> listReply(PageRequestDTO pageRequestDTO, Long tno);

}
