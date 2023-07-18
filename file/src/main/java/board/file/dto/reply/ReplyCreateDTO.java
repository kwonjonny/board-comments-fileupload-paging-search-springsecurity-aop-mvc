package board.file.dto.reply;

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
public class ReplyCreateDTO {
    // tbl_reply
    private Long tno;

    @NotBlank(message = "Reply Is Required")
    private String reply;

    @NotBlank(message = "Replyer Is Required")
    private String replyer;

    @Builder.Default
    private Long gno = 0L;
    private Long rno;
}
