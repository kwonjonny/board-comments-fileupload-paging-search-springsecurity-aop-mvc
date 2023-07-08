package board.file.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    
    @PreAuthorize("permitAll")
    @GetMapping("/signp")
    public void signup() {
        log.info("GET | /member/signup");
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    // ("hasRole('USER')")
    // ("hasRole('ADMIN')")
    @GetMapping("/mypage")
    public void myPage() {
        log.info("GET | /member/mypage");
    }

    @PreAuthorize("permitAll")
    @GetMapping("/signin")
    public void signin() {
        log.info("GET | /member/signin");
    }
    
}
