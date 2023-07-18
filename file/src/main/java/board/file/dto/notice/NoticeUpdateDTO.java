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
public class NoticeUpdateDTO {
    // tbl_notice
    private Long nno;

    @NotBlank(message = "Title Is Required")
    private String title;

    @NotBlank(message = "Writer Is Required")
    private String writer;

    @NotBlank(message = "Content Is Required")
    private String content;
    private List<String> fileNames;
    private LocalDate updateDate;
}
