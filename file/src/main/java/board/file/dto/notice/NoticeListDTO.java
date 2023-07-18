package board.file.dto.notice;

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
public class NoticeListDTO {
    // tbl_notice
    private Long nno;

    @NotBlank(message = "Content Is Required")
    private String title;

    @NotBlank(message = "Content Is Required")
    private String content;

    @NotBlank(message = "Content Is Required")
    private String writer;
    
    private LocalDate registDate;
    private LocalDate updateDate;
    private int replyCnt;
    private String fileName;
    private Long viewCnt;
}
