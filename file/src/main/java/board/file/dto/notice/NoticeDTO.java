package board.file.dto.notice;

import java.time.LocalDate;
import java.util.List;

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
public class NoticeDTO {
    // tbl_notice
    private Long nno;

    @NotBlank(message = "Content Is Required")
    private String content;

    @NotBlank(message = "Writer Is Required")
    private String writer;

    @NotBlank(message = "Title Is Required")
    private String title;
    
    private LocalDate registDate;
    private LocalDate updateDate;
    private List<String> fileName;
    private Long likeCnt;
}
