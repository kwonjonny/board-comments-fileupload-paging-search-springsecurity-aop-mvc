package board.file.dto.board;

import java.util.ArrayList;
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
public class BoardCreateDTO {
    // tbl_board
    private Long tno;
    private String title;
    private String writer;
    private String content;
    @Builder.Default
    private List<String> fileNames = new ArrayList<>();
    /*
     * List<String> 의 형태로 BoardMapper id =
     * " int createBoard(BoardCreateDTO boardCreateDTO);"
     * FileMapper id = "int createImage(List<Map<String,String>> imageList);
     * 입니다 BoardServiceImpl 클래스 내에서 두개의 메소드를 동시에 활용합니다
     */
}
