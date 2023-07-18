package board.file.dto.board;

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
public class BoardListDTO {
    // tbl_board
    private Long tno;

    @NotBlank(message = "Title Is Required")
    private String title;

    @NotBlank(message = "Content Is Required")
    private String content;

    @NotBlank(message = "Writer Is Required")
    private String writer;

    private LocalDate registDate;
    private LocalDate updateDate;
    private int replyCnt;
    private String fileName;
    private Long viewCnt;
    
    private Long nno;
    private String type;
    /*
     * String 으로 BoardMapper id =
     * "List<BoardListDTO> listBoard(PageRequestDTO pageRequestDTO);"
     * 입니다 이 클래스는 List의 형태로 받아주지 않는이유는 BoardMapper.XML 쿼리를 보시면 알겠듯이
     * Ord = 0 일때만 하나의 사진만 조회해오기때문에 ResultMap도 쓸필요 없이 Board Read Page 처럼
     * <img th:src="@{|http://localhost/${fileName}|}" class="my-2"> 파이프 라인을 써주시면
     * 됩니다
     * 다만 다른점은 Board Read Page 와 다르게 forEach를 안써도됩니다
     */
}
