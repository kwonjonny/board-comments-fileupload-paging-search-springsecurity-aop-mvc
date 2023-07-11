package board.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.member.MemberConvertDTO;
import board.file.mappers.MemberMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

    // 의존성 주입
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    // Autowired 명시적 표시
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        log.info("Constructor Called, Member Mapper Injected.");
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Join Member 
    @Override
    @Transactional
    public int joinMember(MemberConvertDTO memberConvertDTO) {
        String passwordEncode = passwordEncoder.encode(memberConvertDTO.getMpw());
        memberConvertDTO.setMpw(passwordEncode);
        String roleName = "USER";
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), roleName);
        return memberMapper.joinMember(memberConvertDTO);
    }

    // Update Member 
    @Override
    @Transactional
    public int updateMember(MemberConvertDTO memberConvertDTO) {
        String passwordEncode = passwordEncoder.encode(memberConvertDTO.getMpw());
        memberConvertDTO.setMpw(passwordEncode);
        return memberMapper.updateMember(memberConvertDTO);
    }

    // Delete Member 
    @Override
    @Transactional
    public int deleteMember(String email) {
        memberMapper.deleteMemberRole(email);
        return memberMapper.deleteMember(email);
    }

    // Read Member 
    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO readMember(String email) {
        return memberMapper.selectMember(email);
    }
}
