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
public class BoardDTO {
    // tbl_board
    private Long tno;
    private String title;
    private String content;
    private String writer;
    private LocalDate registDate;
    private LocalDate updateDate;
    private int replyCnt;
    private List<String> fileName;
    private Long likeCnt;

    /*
     * List<String> 의 형태로 BoardMapper id = "BoardDTO readBoard(Long tno);"
     * BoardMapper에서 LEFT OUTER JOIN 을 통해 List 문자열 형태로 배열로 받습니다
     * ResultMap을 통해 하나의 게시글과 하나의 파일만 조회해오는것이 아닌 하나의 게시물에 여러개의
     * 파일을 조회하게 했습니다 BoardController 에선 BoardDTO list = boardServce.readBoard(tno);
     * model.addAttribute("list", list); 를 통해 Board Read Page 에선 파이프라인으로 Enginx에
     * 직접접근해
     * 여러개의 파일을 띄워주었고 Board Update Page 에선 const fileNames = [[${list.fileName}]] 으로
     * 배열의 형태로 받아 uuid filename link 의 형태로 변환해 동적으로 파일을 띄워주며 삭제도 가능하게했습니다
     * 
     */
}
