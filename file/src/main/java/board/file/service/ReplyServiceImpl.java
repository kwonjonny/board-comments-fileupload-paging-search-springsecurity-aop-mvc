package board.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;
import board.file.mappers.ReplyMapper;
import lombok.extern.log4j.Log4j2;

// Reply ServiceImpl Class 
@Log4j2
@Service
public class ReplyServiceImpl implements ReplyService {

    // 의존성 주입 
    private final ReplyMapper replyMapper;

    // Autowired 명시적 표시 
    @Autowired
    public ReplyServiceImpl(ReplyMapper replyMapper) {
        log.info("Constructor Called, Mapper Injected.");
        this.replyMapper = replyMapper;
    }

    // Create Reply & Reply Child ServiceImpl
    @Override
    @Transactional
    public Long createReply(ReplyCreateDTO replyCreateDTO) {
        log.info("Create ServiceImpl Is Running");
        Long result = null;
        Long gno = replyCreateDTO.getGno();
        if(gno == null || gno == 0) {
            int count = replyMapper.createReply(replyCreateDTO);
            if(count != 1) {
                throw new RuntimeException("Failed Create Reply");
            }
            Long rno = replyCreateDTO.getRno();
            replyMapper.updateReplyGno(gno);
            replyMapper.incrementReplyCount(replyCreateDTO.getTno());
            result = rno;
        } else {
            int count = replyMapper.createReplyChild(replyCreateDTO);
            if(count != 1) {
                throw new RuntimeException("Failed Create Reply Child");
            }
            result = replyCreateDTO.getRno();
            replyMapper.incrementReplyCount(replyCreateDTO.getTno());
        }
        return result;
    }

    // Delete Reply ServiceImpl 
    @Override
    @Transactional
    public int deleteReply(Long rno) {
        log.info("Delete ServiceImpl Is Running");
        ReplyDTO replyDTO = replyMapper.readReply(rno);
        if(replyDTO == null) {
            throw new RuntimeException("Reply not found for rno: " + rno);
        }
        Long tno = replyDTO.getTno();
        replyMapper.deleteReply(rno);
        return replyMapper.decrementReplyCount(tno);
    }

    // Update Reply ServiceImpl
    @Override
    @Transactional
    public int updateReply(ReplyUpdateDTO replyUpdateDTO) {
        log.info("Update ServiceImpl Is Runing");
        return replyMapper.updateReply(replyUpdateDTO);
    }

    // Read Reply ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public ReplyDTO readReply(Long rno) {
        log.info("Read ServiceImpl Is Running");
        return replyMapper.readReply(rno);
    }

    // List Reply ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<ReplyDTO> listReply(PageRequestDTO pageRequestDTO, Long tno) {
        log.info("List ServiceImpl Is Running");
        List<ReplyDTO> list = replyMapper.listReply(pageRequestDTO, tno);
        int total = replyMapper.totalReply(tno);

        return PageResponseDTO.<ReplyDTO>withAll()
        .list(list)
        .total(total)
        .build();
    }
}
