package board.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

// Spring Secuirty Member Controller Class 
@Log4j2
@Controller
@RequestMapping("/member/")
public class MemberController {
    
    // GET : Login
    @GetMapping("login")
    public String getLogin() {
        log.info("GET | Member Login");
        return "/member/login";
    }

    // GET : MyPage
    @GetMapping("mypage")
    public String getMyPage() {
        log.info("GET | Member My Page");
        return "/member/mypage";
    }

    // GET : Join Member 
    @GetMapping("join")
    public String getJoinMember() {
        log.info("GET | Member Join");
        return "/member/join";
    }

    // Post : Join Member 
    @PostMapping("join")
    public String postJoinMember() {
        log.info("GET | Member join");
        return "redirect:/member/mypage";
    }


}
