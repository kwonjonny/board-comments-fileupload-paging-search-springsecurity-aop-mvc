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

    // Create Like
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> createLike(@RequestBody LikeDTO likeDTO,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        likeDTO.setEmail(email);
        log.info("RestController | Create Like");
        int result = likeService.createLike(likeDTO);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Delete Like
    @PostMapping("delete")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> deleteLike(@RequestBody LikeDTO likeDTO,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        likeDTO.setEmail(email);
        log.info("RestController | Delete Like");
        int result = likeService.deleteLike(likeDTO);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Count Like
    @GetMapping("count")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Map<String, Integer>> countLike(@PathVariable("tno") Long tno) {
        log.info("RestController | Count Like");
        int result = likeService.countLike(tno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }
}
