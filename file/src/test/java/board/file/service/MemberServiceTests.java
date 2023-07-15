package board.file.service;

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

// Member Service Test Class
@Log4j2
@SpringBootTest
public class MemberServiceTests {

    // 의존성 주입
    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String TEST_EMAIL = "thisfews2@naver.com";
    private static final String TEST_PASSWORD = "1weifdlsv";
    private static final String TEST_MEMBER_NAME = "권성준";

    // BeforeEach 사용을 위한 MemberConvertDTO 정의
    private MemberConvertDTO memberConvertDTO;
    private MemberConvertDTO memberUpdateDTO;

    // MemberService Create Member Test Set Up
    @BeforeEach
    public void setUp() {
        memberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL)
                .mpw(passwordEncoder.encode(TEST_PASSWORD))
                .mname(TEST_MEMBER_NAME)
                .build();

        memberUpdateDTO = MemberConvertDTO.builder()
                .email("thistrik@naver.com")
                .mpw(passwordEncoder.encode("1111"))
                .mname("권성준")
                .build();
    }

    // Member Join Service Test
    @Test
    @Transactional
    @DisplayName("회원가입 테스트 서비스")
    public void joinMemberServiceTest() {
        // GIVEN
        log.info("=== Start Join Member Service Test ===");
        // WHEN
        memberService.joinMember(memberConvertDTO);
        // THEN
        MemberConvertDTO readMember = memberService.readMember(TEST_EMAIL);
        Assertions.assertNotNull(readMember);
        log.info("=== ENd Join Member Service Test ===");
    }

    // Member Delete Service
    @Test
    @Transactional
    @DisplayName("회원 탈퇴 테스트")
    public void deleteMemberServiceTest() {
        // GIVEN
        log.info("=== Start Delete Member Service Test ===");
        // WHEN
        memberService.deleteMember(TEST_EMAIL);
        // THEN
        MemberConvertDTO deletedMember = memberService.readMember(TEST_EMAIL);
        Assertions.assertNull(deletedMember, "deletedMember Should Be Null");
        log.info("=== End Delete Member Service Test ===");
    }

    // Member Read Service
    @Test
    @Transactional
    @DisplayName("회원 조회 테스트")
    public void readMemberServiceTest() {
        // GIVEN
        log.info("=== Start Read Member Service Test ===");
        // WHEN
        MemberConvertDTO readMember = memberService.readMember("thistrik@naver.com");
        // THEN
        Assertions.assertNotNull(readMember, "readMember Should Be Not Null");
        log.info("=== End Read Member Service Test ===");
    }

    // Member Update Service
    @Test
    @Transactional
    @DisplayName("회원 업데이트 테스트")
    public void updateMemberServiceTest() {
        // GIVEN
        log.info("=== Start Update Member Service Test ===");
        // WHEN
        memberService.updateMember(memberUpdateDTO);
        // THEN
        MemberConvertDTO updatedMember = memberService.readMember("thistrik@naver.com");
        Assertions.assertNotNull(updatedMember);
        log.info("=== End Update Member SErvice Test ===");
    }
}
