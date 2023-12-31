package board.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeListDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.service.NoticeService;
import board.file.util.ManagementCookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

// Notice Controller Class 
@Log4j2
@Controller
@RequestMapping("/notice/")
public class NoticeController {

    // 의존성 주입
    private final NoticeService noticeService;
    private final ManagementCookie managementCookie;

    // Autowired 명시적 표시
    @Autowired
    public NoticeController(NoticeService noticeService, ManagementCookie managementCookie) {
        log.info("Constructor Called, Service Injected.");
        this.noticeService = noticeService;
        this.managementCookie = managementCookie;
    }

    // GET : Create Notice
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public String getCreateNotice() {
        log.info("GET | Notice Create");
        return "/notice/create";
    }

    // GET : List Notice
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('USER')")
    public String getListNotice(PageRequestDTO pageRequestDTO, Authentication authentication, Model model) {
        log.info("GET | Notice List");
        PageResponseDTO<NoticeListDTO> list = noticeService.listNotice(pageRequestDTO);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        model.addAttribute("list", list);
        model.addAttribute("email", email);
        return "/notice/list";
    }

    // GET : Read Notice
    @GetMapping("read/{nno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getReadNotice(PageRequestDTO pageRequestDTO, @PathVariable("nno") Long nno, Model model,
            HttpServletRequest request,
            HttpServletResponse response) {
        log.info("GET | Notice Read");
        if (managementCookie.createCookie(request, response, nno)) {
            noticeService.viewCount(nno);
            log.info("Making Cookie");
        }
        NoticeDTO list = noticeService.readNotice(nno);
        model.addAttribute("list", list);
        return "/notice/read";
    }

    // GET : Update Notice
    @GetMapping("update/{nno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateNotice(PageRequestDTO pageRequestDTO, @PathVariable("nno") Long nno, Model model) {
        log.info("GET | Notice Update");
        NoticeDTO list = noticeService.readNotice(nno);
        model.addAttribute("list", list);
        return "/notice/update";
    }

    // POST : Update Notice
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('USER')")
    public String postUpdateNotice(@Valid NoticeUpdateDTO noticeUpdateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Notice Update");
        noticeService.updateNotice(noticeUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "공지사항이 업데이트 되었습니다.");
        return "redirect:/notice/read/" + noticeUpdateDTO.getNno();
    }

    // POST : Delete Notice
    @PostMapping("delete/{nno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String postDeleteNotice(@PathVariable("nno") Long nno, RedirectAttributes redirectAttributes) {
        log.info("POST | Notice Delete");
        noticeService.deleteNotice(nno);
        redirectAttributes.addFlashAttribute("message", "공지사항이 삭제되었습니다.");
        return "redirect:/notice/list";
    }

    // POST : Create Notice
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public String postCreateNotice(@Valid NoticeCreateDTO noticeCreateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Notice Create");
        Long nno = noticeService.createNotice(noticeCreateDTO);
        redirectAttributes.addFlashAttribute("message", noticeCreateDTO.getNno() + " 번 게시물로 등록되었습니다.");
        return "redirect:/notice/list";
    }
}
