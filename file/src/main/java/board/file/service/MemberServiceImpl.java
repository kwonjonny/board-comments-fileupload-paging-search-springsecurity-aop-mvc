package board.file.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.member.MemberConvertDTO;
import board.file.exception.InvalidEmailException;
import board.file.exception.UserEmailAlreadyExistsException;
import board.file.exception.UserNotFoundException;
import board.file.mappers.MemberMapper;
import lombok.extern.log4j.Log4j2;

// Memeber ServiceImpl Class 
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
        log.info("Join Member MeberServiceImpl Is Running");
        alreadyUserEmailExists(memberConvertDTO.getEmail()); // Check Email
        validationUserEmail(memberConvertDTO.getEmail()); // Validation Check
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
        log.info("Update Member MeberServiceImpl Is Running");
        validationUserEmail(memberConvertDTO.getEmail()); // Validation Check
        String passwordEncode = passwordEncoder.encode(memberConvertDTO.getMpw());
        memberConvertDTO.setMpw(passwordEncode);
        return memberMapper.updateMember(memberConvertDTO);
    }

    // Delete Member
    @Override
    @Transactional
    public int deleteMember(String email) {
        notFoundUser(email); // Check Email
        validationUserEmail(email); // Validation Check
        log.info("Delete Member MeberServiceImpl Is Running");
        memberMapper.deleteMemberRole(email);
        return memberMapper.deleteMember(email);
    }

    // Read Member
    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO readMember(String email) {
        notFoundUser(email); // Check Email
        validationUserEmail(email); // Validation Check
        log.info("Read Member MeberServiceImpl Is Running");
        return memberMapper.selectMember(email);
    }

    // Check Member Email
    @Override
    @Transactional(readOnly = true)
    public void alreadyUserEmailExists(String email) {
        log.info("Already User Email MemberServiceImpl Is Running");
        if (memberMapper.checkMemberEmail(email) == 1) {
            throw new UserEmailAlreadyExistsException("이미 가입된 사용자 입니다.");
        }
    }

    // Not Found User
    @Override
    @Transactional(readOnly = true)
    public void notFoundUser(String email) {
        log.info("Not Found User MemberServiceImpl Is Running");
        if (memberMapper.checkMemberEmail(email) == 0) {
            throw new UserNotFoundException("해당하는 " + email + " 사용자가 없습니다.");
        }
    }

    // Validation User Email
    @Override
    @Transactional(readOnly = true)
    public void validationUserEmail(String email) {
        log.info("Validation User Email MemberServiceImpl Is Running");
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("이메일 형식이 올바르지 않습니다: " + email);
        }
    }
}
