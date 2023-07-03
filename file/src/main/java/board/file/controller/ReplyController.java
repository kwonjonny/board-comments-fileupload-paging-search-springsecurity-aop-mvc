package board.file.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.dto.reply.ReplyCreateDTO;
import board.file.dto.reply.ReplyDTO;
import board.file.dto.reply.ReplyUpdateDTO;
import board.file.service.ReplyService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/replies/")
public class ReplyController {

    // 의존성 주입 
    private final ReplyService replyService;

    // Autowired 명시적 표시 
    @Autowired
    public ReplyController(ReplyService replyService) {
        log.info("Constructor Called, Service Injected.");
        this.replyService = replyService;
    }

    // List Reply
    @GetMapping("{tno}/list")
    public ResponseEntity<PageResponseDTO<ReplyDTO>> getListReply(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO) {
        log.info("RestController : Is Running List Reply");
        PageResponseDTO<ReplyDTO> replyList = replyService.listReply(pageRequestDTO, tno);
        return new ResponseEntity<>(replyList, HttpStatus.OK);
    }

   // Create Reply & Reply Child 
    @PostMapping("{tno}/create")
    public ResponseEntity<Map<String, Long>> postCreateReply(@PathVariable("tno") Long tno, @RequestBody ReplyCreateDTO replyCreateDTO) {
        log.info("RestController : Is Running Create Reply");
        Long insertCount = replyService.createReply(replyCreateDTO);
        return new ResponseEntity<>(Map.of("insertCount", insertCount), HttpStatus.CREATED);
    }

    // Delete Reply 
    @DeleteMapping("{rno}")
    public ResponseEntity<Map<String, String>> deleteReply(@PathVariable("rno") Long rno) {
        log.info("RestController : Is Running Delete Reply");
        replyService.deleteReply(rno);
        return new ResponseEntity<>(Map.of("message", "삭제가 완료되었습니다."), HttpStatus.NO_CONTENT);
    }


   // Update Reply
    @PutMapping("update")
    public ResponseEntity<Map<String, String>> updateReply(@RequestBody ReplyUpdateDTO replyUpdateDTO) {
        log.info("RestController : Is Running Update Reply");
        replyService.updateReply(replyUpdateDTO);
        return new ResponseEntity<>(Map.of("message", "업데이트가 완료되었습니다."), HttpStatus.OK);
    }


    // Read Reply 
    @GetMapping("{rno}")
    public ResponseEntity<ReplyDTO> readReply(@PathVariable("rno") Long rno) {
        log.info("RestController : Is Running Read Reply");
        ReplyDTO replyDTO = replyService.readReply(rno);
        return new ResponseEntity<>(replyDTO, HttpStatus.OK);
    }
}
