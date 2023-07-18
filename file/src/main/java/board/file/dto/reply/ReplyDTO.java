package board.file.dto.reply;

import java.time.LocalDate;

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
public class ReplyDTO {
    // tbl_reply
    private Long rno;
    private Long tno;

    @NotBlank(message = "Reply Is Required")
    private String reply;

    @NotBlank(message = "Repyler Is Required")
    private String replyer;
    private LocalDate replyDate;
    private LocalDate modifyDate;
    private Long isDeleted;

    @Builder.Default
    private Long gno = 0L;
    private int step;
}
