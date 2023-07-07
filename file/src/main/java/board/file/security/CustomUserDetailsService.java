package board.file.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 의존성 주입
    private final MemberMapper memberMapper;

    // Autowired 명시적 표시
    @Autowired
    public CustomUserDetailsService(MemberMapper memberMapper) {
        log.info("Constructor Called, CustomUserDetailsService Injected.");
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // mapper를통해 member 조회
        MemberReadDTO memberReadDTO = memberMapper.readMember(username);
        log.info("loadUserByUsername MemberReadDTO: "+memberReadDTO);

        MemberDTO memberDTO = new MemberDTO(
                username,
                memberReadDTO.getMemberPassword(),
                memberReadDTO.getMemberName(),
                memberReadDTO.getRolenames());

        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode("1111"))
                .authorities("ROLE_USER") // 권한 부여
                .build();

        return user;
    }
}
