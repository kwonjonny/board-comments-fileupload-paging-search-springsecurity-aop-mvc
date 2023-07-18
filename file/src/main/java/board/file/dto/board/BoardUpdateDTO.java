package board.file.dto.board;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class BoardUpdateDTO {
    // tbl_board
    private Long tno;

    @NotBlank(message = "Title Is Required")
    private String title;

    @NotBlank(message = "Content Is Required")
    private String content;

    @NotBlank(message = "Writer Is Required")
    private String writer;

    private LocalDate updateDate;
    
    @Builder.Default
    private List<String> fileNames = new ArrayList<>();
}
