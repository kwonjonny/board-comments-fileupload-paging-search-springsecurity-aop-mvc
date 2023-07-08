package board.file.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import board.file.dto.member.MemberDTO;
import board.file.dto.member.MemberReadDTO;
import board.file.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2     // log4j2 정의 
@Service    // Service bean 정의 
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MemberMapper memberMapper;

    // loadUserByUsername 메소드 정의 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("========== UserDetails is ready ==========");
        log.info(username);
        log.info("========== UserDetails is starting ==========");

        // mapper를통해 member 조회 
        MemberReadDTO readDTO = memberMapper.selectOne(username);

        log.info(readDTO);

        log.info("===========================");

        MemberDTO memberDTO = new MemberDTO(
        username,
        readDTO.getNpw(),
        readDTO.getMname(), 
        readDTO.getRolenames());


        UserDetails user = User.builder()
        .username(username)
        .password(passwordEncoder.encode("1111"))
        .authorities("ROLE_USER", "ROLE_ADMIN")   // 권한 부여 
        .build();
        return user;
    }
    
}
