package board.file.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.file.dto.like.LikeDTO;
import board.file.service.LikeService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/like/")
public class LikeController {

    // 의존성 주입
    private final LikeService likeService;

    // Autowired 명시적 표시
    @Autowired
    public LikeController(LikeService likeService) {
        log.info("Constructor Called, Service Injected.");
        this.likeService = likeService;
    }

    // Toggle Like
    @PostMapping("toggle/{tno}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> toggleLike(@PathVariable("tno") Long tno, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        log.info("RestController | Toggle Like");
        log.info("tno: " + tno + ", email: " + email);
        int result = likeService.toggleLike(tno, email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Count Like
    @GetMapping("{tno}/count")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Long>> countLike(@PathVariable("tno") Long tno) {
        log.info("RestController | Count Like");
        Long result = likeService.countLike(tno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }
}
