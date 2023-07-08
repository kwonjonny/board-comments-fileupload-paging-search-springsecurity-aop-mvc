package board.file.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;

// Board Mapper Class 
@Mapper
public interface BoardMapper {

    // List Board & Read Image
    List<BoardListDTO> listBoard(PageRequestDTO pageRequestDTO);

    // Read Board & Read Image
    BoardDTO readBoard(Long tno);

    // Create Board
    int createBoard(BoardCreateDTO boardCreateDTO);

    // Delete Board
    int deleteBoard(Long tno);

    // Update Board
    int updateBoard(BoardUpdateDTO boardUpdateDTO);

    // Total Board
    int total(PageRequestDTO pageRequestDTO);

    // Board ViewCount 
    int viewCount(Long tno);

    // Board LikeCount
    int likeCount(BoardDTO boardDTO);
}
