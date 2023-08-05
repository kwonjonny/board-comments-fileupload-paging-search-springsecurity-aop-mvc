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
import board.file.exception.BoardNumberNotFoundException;
import board.file.exception.ReplyNumberNotFoundException;
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
        if (gno == null || gno == 0) {
            int count = replyMapper.createReply(replyCreateDTO);
            if (count != 1) {
                throw new RuntimeException("Failed Create Reply");
            }
            Long rno = replyCreateDTO.getRno();
            replyMapper.updateReplyGno(rno, rno);
            replyMapper.incrementReplyCount(replyCreateDTO.getTno());
            result = rno;
        } else {
            int count = replyMapper.createReplyChild(replyCreateDTO);
            if (count != 1) {
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
        notFoundRno(rno); // Check Rno
        ReplyDTO replyDTO = replyMapper.readReply(rno);
        if (replyDTO == null) {
            throw new RuntimeException("Reply not found for rno: " + rno);
        }
        notFoundTno(replyDTO.getTno()); // Check Tno
        Long tno = replyDTO.getTno();
        replyMapper.deleteReply(rno);
        return replyMapper.decrementReplyCount(tno);
    }

    // Update Reply ServiceImpl
    @Override
    @Transactional
    public int updateReply(ReplyUpdateDTO replyUpdateDTO) {
        log.info("Update ServiceImpl Is Runing");
        notFoundRno(replyUpdateDTO.getRno()); // Check Rno
        notFoundTno(replyUpdateDTO.getTno()); // Check Tno
        return replyMapper.updateReply(replyUpdateDTO);
    }

    // Read Reply ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public ReplyDTO readReply(Long rno) {
        log.info("Read ServiceImpl Is Running");
        notFoundRno(rno); // Check Rno
        return replyMapper.readReply(rno);
    }

    // List Reply ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<ReplyDTO> listReply(PageRequestDTO pageRequestDTO, Long tno) {
        log.info("List ServiceImpl Is Running");
        notFoundTno(tno); // Check Tno
        pageRequestDTO.setSize(10);
        int total = replyMapper.totalReply(tno);
        // page 번호
        int pageNum = pageRequestDTO.getPage();
        // 끝 페이지 계산
        if (!pageRequestDTO.isReplyLast()) {
            // pageNum 에 넣어주기
            pageNum = (int) (Math.ceil(total / (double) pageRequestDTO.getSize()));
            // page 번호가 0 보다 작거나 같으면 1
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }
        // 끝페이지 번호로 설정
        pageRequestDTO.setPage(pageNum);
        List<ReplyDTO> list = replyMapper.listReply(pageRequestDTO, tno);
        return PageResponseDTO.<ReplyDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // Not Found Rno ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public void notFoundRno(Long rno) {
        log.info("Not Found Rno ServiceImpl Is Runnig");
        if (replyMapper.checkRno(rno) == 0) {
            throw new ReplyNumberNotFoundException("해당하는 댓글 번호가 없습니다.");
        }
    }

    // Not Found Tno serviceImpl
    @Override
    @Transactional(readOnly = true)
    public void notFoundTno(Long tno) {
        log.info("Not Found Tno ServiceImpl Is Running");
        if (replyMapper.checkTno(tno) == 0) {
            throw new BoardNumberNotFoundException("해당하는 게시물 번호가 없습니다.");
        }
    }
}