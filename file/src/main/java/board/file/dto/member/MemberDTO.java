package board.file.dto.member;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO extends User implements OAuth2User {

  private String mname;
  private String email;
  private String mpw;

  public MemberDTO(String email, String mpw, String mname, List<String> roleNames) {
    
    // super(email,mpw, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    super(email, mpw, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
    this.mname = mname;
    this.email = email;
    this.mpw = mpw;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public String getName() {
    return this.email;
  }

}