package board.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.file.service.ReplyService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/replies/")
public class ReplyController {

    // 의존성 주입 
    private final ReplyService replyService;

    // Autworied 명시적 표시 
    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    
}
