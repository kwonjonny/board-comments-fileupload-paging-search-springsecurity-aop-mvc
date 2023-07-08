package board.file.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
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

                log.info("=================");
                log.info("=================");

                log.info(authentication.getPrincipal());

                MemberDTO dto = (MemberDTO) authentication.getPrincipal();

                log.info(dto);

                log.info("=================");
                log.info("=================");

                // MemberDTO를 사용 소셜 로그인에 성공했을 시(mpw가 "" 일때) 수정페이지로 가기
                if(dto.getPw() == null || dto.getPw().equals("")){

                    // DB에 소셜로그인 email이 없을때
                    response.sendRedirect("/member/update");
                    return;
                }
                // DB email 컬럼에 소셜로그인 이메일이 존재할 시 myPage 
                response.sendRedirect("/member/mypage/" + dto.getEmail());
    }

}