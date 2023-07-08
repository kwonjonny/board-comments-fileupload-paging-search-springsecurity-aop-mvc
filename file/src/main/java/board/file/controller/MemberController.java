package board.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.file.dto.member.MemberConvertDTO;
import board.file.dto.member.MemberDTO;
import board.file.service.MemberService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/")
public class MemberController {

    // 의존성 주입
    private final MemberService memberService;

    // Autowired 명시적 표시
    @Autowired
    public MemberController(MemberService memberService) {
        log.info("Constructor Called, Service Injected.");
        this.memberService = memberService;
    }

    // GET : Member Signin
    @GetMapping("signin")
    @PreAuthorize("permitAll")
    public String signin() {
        log.info("GET | Member Signin");
        return "/member/signin";
    }

    // GET : Member Join 
    @GetMapping("join")
    @PreAuthorize("permitAll")
    public String getJoin() {
        log.info("GET | Member Join");
        return "/member/join";
    }

    // GET : Member My Page
    @GetMapping("mypage/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String myPage(@PathVariable("email") String email, Model model) {
        log.info("GET | Member My page");
        MemberConvertDTO member = memberService.readMember(email);
        model.addAttribute("member", member);
        return "/member/mypage";
    }

    // GET : Member My Update Page
    @GetMapping("update/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateMember(@PathVariable("email") String email, Model model) {
        log.info("GET | Get Member Update");
        MemberConvertDTO member = memberService.readMember(email);
        model.addAttribute("member", member);
        return "/member/update";
    }

    // POST : Member My Update Page
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('USER')")
    public String postUpdateMember(MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Post Member Update");
        memberService.updateMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 업데이트 완료");
        return "redirect:/member/mypage/" + memberConvertDTO.getEmail();
    }

    // POST : Member Join 
    @PostMapping("join")
    @PreAuthorize("permitAll")
    public String postJoinMember(MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Post Member Join");
        memberService.joinMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
        return "redirect:/member/signin";
    }

    // POST : Member Delete
    @PostMapping("delete/{email}")
    @PreAuthorize("hasAnyRole('USER')")
    public String postDelteMember(@PathVariable("email") String email, RedirectAttributes redirectAttributes) {
        log.info("POST | Post Member Delete");
        memberService.deleteMember(email);
        redirectAttributes.addFlashAttribute("message", "회원 삭제 완료");
        return "redirect:/member/signin";
    }
}
