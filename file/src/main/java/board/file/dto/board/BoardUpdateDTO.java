package board.file.dto.board;

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
public class BoardUpdateDTO {
    // tbl_board
    private Long tno;
    private String title;
    private String content;
    private String writer;
    private LocalDate updateDate;
    private List<String> fileNames;
}
