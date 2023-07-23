package board.file.controller.interceptor;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nimbusds.jose.shaded.gson.Gson;

import board.file.util.JwtiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JWTinterceptor implements HandlerInterceptor {

    private final JwtiUtil jwtiUtil;

    public JWTinterceptor(JwtiUtil jwtiUtil) {
        log.info("Constructor Called, Service Injected.");
        this.jwtiUtil = jwtiUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // header의 메시지를 받아온다
        // Social Login 에 Autorizaiton을 가져와야 한다
        try {
            String headerStr = request.getHeader("Authorization");
            if (headerStr == null || headerStr.isEmpty() || headerStr.length() < 7) {
                throw new JwtiUtil.CustomJWTException("Invalid Authorziation Header Int JWTInterceptor Class");
            }
            String accessToken = request.getHeader("Authorization").substring(7);
            Map<String, Object> claims = jwtiUtil.validateToken(accessToken);
            log.info("JWTInterceptor Class Claims" + claims);
        } catch (Exception e) {
            response.setContentType("application/json");
            Gson gson = new Gson();
            String str = gson.toJson(Map.of("error", e.getMessage()));

            response.getOutputStream().write(str.getBytes());
            return false;
        }

        log.info("=====================");
        log.info("handler: " + handler);
        log.info("=====================");
        log.info("jwtUtil: " + jwtiUtil);
        log.info("=====================");
        log.info("response: " + response);
        return true;
    }
}
