package board.file.dto.member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO extends User implements OAuth2User {
    
    // tbl_member
    private String email;
    private String memberPassword;
    private String memberName;

    public MemberDTO(String email, String memberPassword, String memberName, List<String> roleNames){

    super(email,memberPassword,roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));
    this.memberName = memberName;
    this.email = email;
    this.memberPassword = memberPassword;
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
