package board.file.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.util.StandardCharset;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

// Jwt Token Util Class 
@Log4j2
@Component
public class JwtiUtil {

    // Custom JWT Exception
    public static class CustomJWTException extends RuntimeException {
        public CustomJWTException(String msg) {
            super(msg);
        }
    }

    @Value("${board.file.jwt.secret}")
    private String key;

    public String generateToken(Map<String, Object> claminMap, int min) {
        log.info("Is Running GenereateToken Jwt Util Class");
        // Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "JWT");
        // Clamins
        Map<String, Object> claims = new HashMap<>();

        // Access Key
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(this.key.getBytes(StandardCharset.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Access Key Exception");
        }
        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
        return jwtStr;
    }

    // 토큰 검증 메소드
    public Map<String, Object> validateToken(String token) {
        log.info("Is Running Validate Token Method JwtUtil Class");
        if (token == null) {
            throw new CustomJWTException("Null Pointer Token");
        }

        Map<String, Object> claims = null;
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes(StandardCharset.UTF_8));
            claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();

            // JWT 문자열 구성 잘못
        } catch (MalformedJwtException e) {
            throw new CustomJWTException("Malformed");
        } catch (ExpiredJwtException e) {
            throw new CustomJWTException("Expired");
        } catch (JwtException e) {
            throw new CustomJWTException(e.getMessage());
        } catch (Exception e) {
            throw new CustomJWTException("Something Wrong");
        }
        return claims;
    }
}
