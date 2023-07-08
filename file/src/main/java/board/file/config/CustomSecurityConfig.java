package board.file.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import board.file.security.CustomOAuth2UserService;
import board.file.security.handler.CustomAccessDeniedHandler;
import board.file.security.handler.CustomOAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity   // @Preauthorize 쓰려고 등록 
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomOAuth2UserService customOAuth2UserService;
    

    // passwordEncoder 정의 bean 등록 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    
    
    // filterChain 메소드 정의 bean 등록 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        
        // seucirty statring log check 
        log.info("=================== security config is ready ======================");

        // default seucirty login page use 
        // httpSecurity.formLogin(Customizer.withDefaults());

        // we will use custom login page 
        httpSecurity.formLogin(config -> {
            config.loginPage("/member/signin");
            config.successHandler(new CustomOAuthSuccessHandler());
        });

        // CustomAccessDeniedHandler.java 클래스의 
        // acess deinedHandler 의 커스텀 메소드 요소를 쓴다 
        httpSecurity.exceptionHandling(config -> {
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });


        // csrf disable 
         httpSecurity.csrf(config -> {
            config.disable();
        });

        // 카카오로그인 경로지정 
        httpSecurity.oauth2Login(config -> {
            config.loginPage("/member/signin");
            config.successHandler(new CustomOAuthSuccessHandler());
        });


        // remberme
        httpSecurity.rememberMe(config -> {
            config.tokenRepository(persistentTokenRepository());
            config.tokenValiditySeconds(60*60*24*7); // 1 week 
        });
    
        // return security build
        return httpSecurity.build();
    }   
}
