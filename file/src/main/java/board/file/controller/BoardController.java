package board.file.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.file.dto.board.BoardCreateDTO;
import board.file.dto.board.BoardDTO;
import board.file.dto.board.BoardListDTO;
import board.file.dto.board.BoardUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.service.BoardService;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

// Board Controller Class 
@Log4j2
@Controller
@RequestMapping("/board/")
public class BoardController {

    // 의존성 주입 
    private final BoardService boardServce;

    // Autowired 명시적 표시
    @Autowired
    public BoardController(BoardService boardService) {
        log.info("Constructor Called, Service Injected.");
        this.boardServce = boardService;
    }

    // GET : Create
    @GetMapping("create2")
    public String getBoardCreate() {
        log.info("GET | Board Create");
        return "/board/create2";
    }

     // GET : List 
    @GetMapping("list")
    public String getBoardList(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardListDTO> list = boardServce.listboard(pageRequestDTO);
        model.addAttribute("list", list);
        log.info("GET | Board List");
        return "/board/list";
    }

    // GET : Read 
    @GetMapping("read/{tno}")
    public String getBoardRead(@PathVariable("tno") Long tno, Model model) {
        log.info("GET | Board Read");
        BoardDTO list = boardServce.readBoard(tno);
        model.addAttribute("list", list);
        return "/board/read" ;
    }

    // GET : Update
    @GetMapping("update2/{tno}")
    public String getBoardUpdate(@PathVariable("tno") Long tno, Model model) {
        log.info("GET | Board Update");
        BoardDTO list = boardServce.readBoard(tno);
        model.addAttribute("list", list);
        return "/board/update2";
    }

    // POST : Delete
    @PostMapping("delete/{tno}")
    public String postBoardDelete(@PathVariable("tno") Long tno, RedirectAttributes redirectAttributes) {
        log.info("POST | Board Delte");
        boardServce.deleteBoard(tno);
        redirectAttributes.addFlashAttribute("message", "게시물이 삭제되었습니다.");
        return "redirect:/board/list";
    } 

    // POST : Update
    @PostMapping("update")
    public String postBoardUpdate(BoardUpdateDTO boardUpdateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Board Update");
        boardServce.updateBoard(boardUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "게시물이 업데이트 되었습니다.");
        return "redirect:/board/read/" + boardUpdateDTO.getTno();
    }

    // POST : Create 
    @PostMapping("/create2")
    public String postBoardCreate(BoardCreateDTO boardCreateDTO,RedirectAttributes redirectAttributes) {
        log.info("POST | Board Create");
        // 게시물 생성 부분
        Long tno = boardServce.createBoard(boardCreateDTO);
        redirectAttributes.addFlashAttribute("message", boardCreateDTO.getTno() + " 번 게시물로 등록되었습니다.");
        return "redirect:/board/list";
    }
}
