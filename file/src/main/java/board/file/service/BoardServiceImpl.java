package board.file.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardNoticeCreateDTO;
import board.file.dto.board.BoardNoticeUpdateDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.mappers.BoardMapper;
import board.file.mappers.FileMapper;
import lombok.extern.log4j.Log4j2;

// Board ServiceImpl Class 
@Log4j2
@Service
public class BoardServiceImpl implements BoardService {

   // 의존성 주입
   private final BoardMapper boardMapper;
   private final FileMapper fileMapper;

   // Autowired 명시적 표시
   @Autowired
   public BoardServiceImpl(BoardMapper boardMapper, FileMapper fileMapper) {
      log.info("Constructor Called, Mapper Injected.");
      this.boardMapper = boardMapper;
      this.fileMapper = fileMapper;
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

   // Create BoardServiceImpl & Create FileServiceImpl
   @Override
   @Transactional
   public Long createBoard(BoardCreateDTO boardCreateDTO) {
      log.info("Create BoardServiceImpl Is Running");
      int count = boardMapper.createBoard(boardCreateDTO);
      AtomicInteger index = new AtomicInteger(0);
      List<String> fileNames = boardCreateDTO.getFileNames();
      Long tno = boardCreateDTO.getTno();

      List<Map<String, String>> list = fileNames.stream().map(str -> {
         String uuid = str.substring(0, 36);
         String fileName = str.substring(37);
         return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      fileMapper.createImage(list);
      return boardCreateDTO.getTno();
   }

   // Update BoardServiceImpl & Update FileServiceImpl
   @Override
   @Transactional
   public Long updateBoard(BoardUpdateDTO boardUpdateDTO) {
      log.info("Update BoardServiceImpl Is Running");
      int count = boardMapper.updateBoard(boardUpdateDTO);
      fileMapper.deleteImage(boardUpdateDTO.getTno());
      AtomicInteger index = new AtomicInteger(0);

      List<String> fileNames = boardUpdateDTO.getFileNames();
      Long tno = boardUpdateDTO.getTno();
      List<Map<String, String>> list = fileNames.stream().map(str -> {
         String uuid = str.substring(0, 36);
         String fileName = str.substring(37);
         return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      fileMapper.createImage(list);
      return boardUpdateDTO.getTno();
   }

   // Delete BoardServiceImpl
   @Override
   @Transactional
   public void deleteBoard(Long tno) {
      log.info("Delete BoardServiceImpl Is Running");
      boardMapper.deleteBoard(tno);
   }

   // Read BoardServiceImpl
   @Override
   @Transactional(readOnly = true)
   public BoardDTO readBoard(Long tno) {
      log.info("Read BoardServiceImpl Is Running");
      return boardMapper.readBoard(tno);
   }

   // Board View Count ServiceImpl
   @Override
   @Transactional
   public void viewCount(Long tno) {
      log.info("ViewCount BoardServiceImpl Is Running");
      boardMapper.viewCount(tno);
   }

   // Board Notice Create ServiceImpl
   @Override
   @Transactional
   public Long createBoardNotice(NoticeCreateDTO boardNoticeCreateDTO) {
      log.info("Create Notice BoardServiceImpl Is Running");
      int count = boardMapper.createBoardNotice(boardNoticeCreateDTO);
      AtomicInteger index = new AtomicInteger(0);
      List<String> fileNames = boardNoticeCreateDTO.getFileNames();
      Long tno = boardNoticeCreateDTO.getTno();

      List<Map<String, String>> list = fileNames.stream().map(str -> {
         String uuid = str.substring(0, 36);
         String fileName = str.substring(37);
         return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      fileMapper.createImage(list);
      return boardNoticeCreateDTO.getTno();
   }

   // Board Notice Update ServiceImpl
   @Override
   @Transactional
   public Long updateBoardNotice(NoticeUpdateDTO boardNoticeUpdateDTO) {
      log.info("Update Notice BoardServiceImpl Is Running");
      int count = boardMapper.updateBoardNotice(boardNoticeUpdateDTO);
      fileMapper.deleteImage(boardNoticeUpdateDTO.getTno());
      AtomicInteger index = new AtomicInteger(0);
      List<String> fileNames = boardNoticeUpdateDTO.getFileNames();
      Long tno = boardNoticeUpdateDTO.getTno();

      List<Map<String, String>> list = fileNames.stream().map(str -> {
         String uuid = str.substring(0, 36);
         String fileName = str.substring(37);
         return Map.of("uuid", uuid, "fileName", fileName, "tno", "" + tno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      fileMapper.createImage(list);
      return boardNoticeUpdateDTO.getTno();
   }

   @Override
   @Transactional(readOnly = true)
   public BoardDTO readBoardNotice(Long tno) {
      return boardMapper.readBoardNotice(tno);
   }
}
