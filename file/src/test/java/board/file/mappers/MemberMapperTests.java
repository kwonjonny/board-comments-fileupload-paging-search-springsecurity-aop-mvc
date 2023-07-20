package board.file.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.member.MemberConvertDTO;
import lombok.extern.log4j.Log4j2;

// Member Mapper Test Class
@Log4j2
@SpringBootTest
public class MemberMapperTests {

    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String TEST_EMAIL = "thisfews2@naver.com";
    private static final String TEST_PASSWORD = "1weifdlsv";
    private static final String TEST_MEMBER_NAME = "권성준";

    // BeforeEach 사용을 위한 MemberConvertDTO 정의
    private MemberConvertDTO memberConvertDTO;

    // MemberService Create Member Test Set Up
    @BeforeEach
    public void setUp() {
        memberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL)
                .mpw(passwordEncoder.encode(TEST_PASSWORD))
                .mname(TEST_MEMBER_NAME)
                .build();
    }

    // Join Member Mapper Test
    @Test
    @Transactional
    @DisplayName("회원 가입 테스트")
    public void joinMemberTest() {
        log.info("=== Start Join Member Mapper Test ===");
        // GIVEN
        String roleName = "USER";
        // WHEN
        memberMapper.joinMember(memberConvertDTO);
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), roleName);
        // THEN
        log.info("=== End Join Member Mapper Test ===");
    }

    // Update Member Mapper Test
    @Test
    @Transactional
    @DisplayName("회원 업데이트 테스트")
    public void updateMemberTest() {
        log.info("Start Update Member Mapper Test ===");
        // GIVEN
        MemberConvertDTO memberConvertDTO = MemberConvertDTO.builder()
                .email("thistrik@naver.com")
                .mpw(passwordEncoder.encode("1111"))
                .mname("김선제")
                .build();
        // WHEN
        memberMapper.updateMember(memberConvertDTO);
        // THEN
        memberMapper.selectMember("thistrik@naver.com");
        Assertions.assertNotNull("thistrik@naver.com", "Should Be Not Null");
        log.info("End Update Member Mapper Test ===");
    }

    // Delete Member Mapper Test
    @Test
    @Transactional
    @DisplayName("회원 삭제 테스트")
    public void deleteMemberTest() {
        log.info("Start Delete Member Mapper Test ===");
        // GIVEN
        String email = "thistrik@naver.com";
        // WHEN
        memberMapper.deleteMember(email);
        memberMapper.deleteMemberRole(email);
        // THEN
        MemberConvertDTO readMember = memberMapper.selectMember(email);
        Assertions.assertNull(readMember, "readMember Should Be Null");
        log.info("End Delete Member Mapper Test ===");
    }

    // Select Member Mapper Test
    @Test
    @Transactional
    @DisplayName("회원 조회 테스트")
    public void selectMemberTest() {
        log.info("Start Read Member Mapper Test ===");
        // GIVEN
        String email = "thistrik@naver.com";
        // WHEN
        MemberConvertDTO readMember = memberMapper.selectMember(email);
        // THEN
        log.info(readMember);
        Assertions.assertNotNull(readMember, "readMember Should Not Null");
        log.info("End Read Member Mapper Test ===");
    }
}
