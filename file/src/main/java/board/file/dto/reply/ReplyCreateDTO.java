package board.file.dto.reply;

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
    private String reply;
    private String replyer;

    @Builder.Default
    private Long gno = 0L;
    private Long rno;
}
