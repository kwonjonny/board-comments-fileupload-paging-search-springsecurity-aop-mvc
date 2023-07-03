package board.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Constructor Called, Mapper Injected.");
        this.boardMapper = boardMapper;
    }
    
    // List BoardServiceImpl 
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<BoardListDTO> listboard(PageRequestDTO pageRequestDTO) {
        log.info("List BoardServiceImpl Is Running");
        List<BoardListDTO> list = boardMapper.listBoard(pageRequestDTO);
        int total = boardMapper.total(pageRequestDTO);

         return PageResponseDTO.<BoardListDTO>withAll()
                .list(list)
                .total(total)
                .build();
    }

    // Create BoardServiceImpl
    @Override
    @Transactional
    public Long createBoard(BoardCreateDTO boardCreateDTO) {
       log.info("Create BoardServiceImpl Is Running");
       boardMapper.createBoard(boardCreateDTO);
       return boardCreateDTO.getTno();
    }

    // Delete BoardServiceImpl
    @Override
    @Transactional
    public void deleteBoard(Long tno) {
      log.info("Delete BoardServiceImpl Is Running");
      boardMapper.deleteBoard(tno);
    }

    // Update BoardServiceImpl
    @Override
    @Transactional
    public void updateBoard(BoardUpdateDTO boardUpdateDTO) {
       log.info("Update BoardService Is Running");
       boardMapper.updateBoard(boardUpdateDTO);
    }

    // Read BoardServiceImpl
    @Override
    @Transactional(readOnly = true)
    public BoardDTO readBoard(Long tno) {
       log.info("Read BoardServiceImpl Is Running");
       return boardMapper.readBoard(tno);
    }
}
