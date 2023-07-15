package board.file.dto.reply;

import java.time.LocalDate;

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
public class ReplyUpdateDTO {
    // tbl_reply
    private Long tno;
    private Long rno;
    private Long gno;
    private String reply;
    private String replyer;
    private LocalDate modifyDate;
}
