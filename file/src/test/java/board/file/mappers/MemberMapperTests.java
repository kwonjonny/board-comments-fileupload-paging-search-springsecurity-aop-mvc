package board.file.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.member.MemberConvertDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberMapperTests {
    
    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Join Member Mapper Test 
    @Test
    @Transactional
    @DisplayName("회원 가입 테스트")
    public void joinMemberTest() {
        // GIVEN 
        MemberConvertDTO memberConvertDTO = MemberConvertDTO.builder()
        .email("thistrik@naver.com")
        .npw(passwordEncoder.encode(("1111")))
        .mname("권성준")
        .build();
        String roleName = "ROLE_USER";
        // WHEN 
        memberMapper.joinMember(memberConvertDTO);
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), roleName);
        // THEN 
    }

    // Update Member Mapper Test 
    @Test
    @Transactional
    @DisplayName("회원 업데이트 테스트")
    public void updateMemberTest() {
        // GIVEN 
        MemberConvertDTO memberConvertDTO = MemberConvertDTO.builder()
        .email("thistrik@naver.com")
        .npw(passwordEncoder.encode("1111"))
        .mname("김선제")
        .build();
        String roleName = "ROLE_USER";
        // WHEN 
        memberMapper.updateMember(memberConvertDTO);
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), roleName);
        // THEN 
        memberMapper.selectMember("thistrik@naver.com");
        Assertions.assertNotNull("thistrik@naver.com", "Should Be Not Null");
    }

    // Delete Member Mapper Test 
    @Test
    @Transactional
    @DisplayName("회원 삭제 테스트")
    public void deleteMemberTest() {
        // GIVEN
        String email = "thistrik@naver.com";
        // WHEN 
        memberMapper.deleteMember(email);
        // THEN 
        MemberConvertDTO readMember = memberMapper.selectMember(email);
        Assertions.assertNull(readMember, "readMember Should Be Null");
    }

    // Select Member Mapper Test 
    @Test
    @Transactional(readOnly = true)
    public void selectMemberTest() {
        // GIVEN
        String email = "thistrik@naver.com";
        // WHEN 
        MemberConvertDTO readMember = memberMapper.selectMember(email);
        // THEN 
        log.info(readMember);
        Assertions.assertNotNull(readMember, "readMember Should Not Null");
    }


}
