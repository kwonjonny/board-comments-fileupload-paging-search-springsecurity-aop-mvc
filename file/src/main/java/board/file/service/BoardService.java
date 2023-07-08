package board.file.service;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;

// Board Service interface 
public interface BoardService {

    // List Board Service
    PageResponseDTO<BoardListDTO> listboard(PageRequestDTO pageRequestDTO);

    // Create Board Service
    Long createBoard(BoardCreateDTO boardCreateDTO);

    // Delete Board Service
    void deleteBoard(Long tno);

    // Update Board Service
    Long updateBoard(BoardUpdateDTO boardUpdateDTO);

    // Read Board Service
    BoardDTO readBoard(Long tno);

    // Board View Count 
    void viewCount(Long tno);

    // Board Like Count 
    void likeStatus(Long tno);
}
