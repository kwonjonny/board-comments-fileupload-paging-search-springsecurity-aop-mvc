package board.file.dto.notice;

import java.time.LocalDate;
import java.util.List;

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
    private String title;
    private String writer;
    private String content;
    private List<String> fileNames;
    private LocalDate updateDate;
}
