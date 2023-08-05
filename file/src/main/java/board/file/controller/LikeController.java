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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.file.service.LikeService;
import lombok.extern.log4j.Log4j2;

// Like Controller Class
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

    // Toggle Like Tno
    @PostMapping("tno/toggle/{tno}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> toggleLikeTno(@PathVariable("tno") Long tno,
            Authentication authentication) {
        log.info("RestController | Toggle Like");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        int result = likeService.toggleLike(tno, email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Count Like Tno
    @GetMapping("tno/{tno}/count")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Long>> countLikeTno(@PathVariable("tno") Long tno) {
        log.info("RestController | Count Like");
        Long result = likeService.countLike(tno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Toggle Like Nno
    @PostMapping("nno/toggle/{nno}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> toggleLikeNno(@PathVariable("nno") Long nno,
            Authentication authentication) {
        log.info("RestController | Toggle Like For Nno");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        int result = likeService.toggleLikeNno(nno, email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Count Like Nno
    @GetMapping("nno/{nno}/count")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Long>> countLikeNno(@PathVariable("nno") Long nno) {
        log.info("RestController | Count Like");
        Long result = likeService.countLikeNno(nno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }
}
