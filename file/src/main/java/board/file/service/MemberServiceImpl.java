package board.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.member.MemberConvertDTO;
import board.file.dto.member.MemberDTO;
import board.file.mappers.MemberMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService {
    
    // 의존성 주입 
    private final MemberMapper memberMapper;

    // Autowired 명시적 표시 
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        log.info("Constructor Called, Member Mapper Injected.");
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO selectMember(String email) {
        return memberMapper.selectMember(email);
    }

    @Override
    @Transactional
    public int joinMember(MemberConvertDTO memberConvertDTO) {
       return memberMapper.joinMember(memberConvertDTO);
    }

    @Override
    @Transactional
    public int updateMember(MemberConvertDTO memberConvertDTO) {
        return memberMapper.updateMember(memberConvertDTO);
    }

    @Override
    @Transactional
    public int deleteMember(String email) {
       return memberMapper.deleteMember(email);
    }
}
