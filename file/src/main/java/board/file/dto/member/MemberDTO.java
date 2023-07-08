package board.file.dto.member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
public class MemberDTO extends User implements OAuth2User {

  private String mname;
  private String email;
  private String pw;

  public MemberDTO(String email, String npw, String mname, List<String> roleNames) {

    super(email, npw, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
    this.mname = mname;
    this.email = email;
    this.pw = npw;
  }

  // Aotuh2User를 구현함으로써 두개의 강제 메소드 정의
  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public String getName() {
    return this.email;
  }

}
