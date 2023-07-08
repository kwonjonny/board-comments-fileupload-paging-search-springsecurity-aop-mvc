package board.file.dto.member;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberReadDTO {

    private String email;
    private String mpw;
    private String mname;
    private List<String> rolenames;
    
}