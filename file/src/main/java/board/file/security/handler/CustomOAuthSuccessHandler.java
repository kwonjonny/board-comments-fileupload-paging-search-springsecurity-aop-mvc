package board.file.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import board.file.dto.member.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("================ onAuthenticationSuceess handler is Ready ===================");

        log.info(authentication + "autehtication");

        log.info(authentication.getPrincipal() + "authentication.getPrincipal");
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            MemberDTO dto = (MemberDTO) authentication.getPrincipal();
            log.info(dto + "CustomOAuthSucessHandler 의 dto");
            // 카카오사용자가 pw 가 null이거나 ""이면 member/modify 로 보낸다
            if (dto.getPw() == null || dto.getPw().equals("")) {
                response.sendRedirect("/member/modify");
                return;
            }
            response.sendRedirect("/member/mypage");
            log.info("================ onAuthenticationSuceess handler is Ready ===================");
        }
    }

}
