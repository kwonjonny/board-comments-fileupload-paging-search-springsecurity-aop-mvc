package board.file.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import board.file.dto.member.MemberDTO;
import board.file.dto.member.MemberReadDTO;
import board.file.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// kakao login 성공 페이지를 보여주기위한 서비스 클래스입니다 
@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    
    private final MemberMapper memberMapper;
    

    // 원하는 정보를 추출하는 서비스 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2User loadUser is ready ==========================");
        log.info(userRequest);
        log.info("=======================================================");

        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        // 네이버 or google ? 
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        switch (clientName){
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        log.info("===============================");
        log.info(email + " : : : : : : : : : :clientEmail");
        log.info("===============================");

        // DB에 해당 사용자가 있다면
        MemberReadDTO dbAuthCheck = memberMapper.selectOne(email); 
        // selectOne 메소드가 사용자의 이메일을 인자로 받아 MemberDTO를 반환한다고 가정합니다.
        if(dbAuthCheck != null) {
            return new MemberDTO(email, dbAuthCheck.getNpw(), dbAuthCheck.getMname(), dbAuthCheck.getRolenames());

        }


        // db에 해당 사용자가 없다면 
        // MemberDTO 정의 
        // 패스워드는 이미 카카오가 인증을 끝내줬으므로 passwordEncoder를 아무거나 집어넣어준다 
        MemberDTO memberDTO = new MemberDTO(email, "", "카카오사용자", List.of("ROLE_USER"));
        log.info("memberDTO"+memberDTO);
        log.info("memberDTO.getMname"+memberDTO.getMname());
        log.info(memberDTO.getUsername());
        // memberDTO반환 해야한다 소셜 로그인을 한 멤버를 memberDTO타입으로 반환해줘야한다 
        // 이유는 : 반환된 사용자의 정보가 똑같아야 된다 
        // 예를들자면 일반로그인사용자와 social login 사용자의 리턴값이 다르면안되니까 MemberDTO의 타입으로 강제 일치 
        return memberDTO;
    }

    // KakaoEmail Get 
    private String getKakaoEmail(Map<String, Object> paramMap){

        log.info("KAKAO-----------------------------------------");

        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String)accountMap.get("email");

        log.info("email..." + email);

        return email;
    }
}
