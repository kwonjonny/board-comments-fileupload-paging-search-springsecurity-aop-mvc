package board.file.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberConvertDTO {
    // tbl_member

    @NotBlank(message = "Email Is Required")
    private String email;

    @NotBlank(message = "Member Password Is Required")
    private String mpw;

    @NotBlank(message = "Memebr Name Is Required")
    private String mname;
}
