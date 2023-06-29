package board.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.mappers.BoardMapper;
import lombok.extern.log4j.Log4j2;

// Board ServiceImpl Class 
@Log4j2
@Service
public class BoardServiceImpl implements BoardService {
    
    // 의존성 주입 
    private final BoardMapper boardMapper;

    // Autowired 명시적 표시 
    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    
    // List BoardServiceImpl 
    @Override
    public PageResponseDTO<BoardListDTO> listboard(PageRequestDTO pageRequestDTO) {
        List<BoardListDTO> list = boardMapper.listBoard(pageRequestDTO);
        int total = boardMapper.total(pageRequestDTO);

         return PageResponseDTO.<BoardListDTO>withAll()
                .list(list)
                .total(total)
                .build();
    }

    // Create BoardServiceImpl
    @Override
    public int createBoard(BoardCreateDTO boardCreateDTO) {
       return boardMapper.createBoard(boardCreateDTO);
    }

    // Delete BoardServiceImpl
    @Override
    public void deleteBoard(Long tno) {
      boardMapper.deleteBoard(tno);
    }

    // Update BoardServiceImpl
    @Override
    public void updateBoard(BoardUpdateDTO boardUpdateDTO) {
       boardMapper.updateBoard(boardUpdateDTO);
    }

    // Read BoardServiceImpl
    @Override
    public BoardDTO readBoard(Long tno) {
       return boardMapper.readBoard(tno);
    }
}
