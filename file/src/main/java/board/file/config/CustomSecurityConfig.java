package board.file.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import board.file.security.handler.CustomAccessDeniedHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity // @Preauthorize using in controller
public class CustomSecurityConfig {

    // 의존성 주입
    private final DataSource dataSource;

    // Autowired 명시적 표시
    @Autowired
    public CustomSecurityConfig(DataSource dataSource) {
        log.info("Constructor Called, DataSource Injected.");
        this.dataSource = dataSource;
    }

    // PasswordEncoder Using
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Is Running PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    // PresistentTokenRepository Using
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    // FilterChain Method Using 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Security Filter Chain Is Running");

        // Using Custom Login Page
        httpSecurity.formLogin(config -> {
            config.loginPage("/member/login");
        });

        // Using ExcptionHanlder 
        httpSecurity.exceptionHandling(config -> {
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        // Csrf Disable
        httpSecurity.csrf(config -> {
            config.disable();
        });

        // Social Login KaKao 
        httpSecurity.oauth2Login(config -> {
            config.loginPage("/mebmer/login");
        });

        // Using RememberMe
        httpSecurity.rememberMe(config -> {
            config.tokenRepository(persistentTokenRepository());
            config.tokenValiditySeconds(60*60*24*7);    // 1 week RememberMe
        });

        return httpSecurity.build();
    }
}
