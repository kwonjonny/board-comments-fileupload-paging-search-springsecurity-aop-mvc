package board.file.service;

import java.util.List;

import board.file.dto.File.FileDTO;
import board.file.dto.File.FileReadDTO;
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

    // read Image
    List<String> getBoardFile(Long tno);

}
