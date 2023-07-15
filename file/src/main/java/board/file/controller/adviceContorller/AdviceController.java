package board.file.controller.adviceContorller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import board.file.controller.BoardController;
import board.file.controller.MemberController;
import board.file.controller.NoticeController;
import board.file.controller.ReplyController.DataNotFoundException;

// 각 Controller 에 대한 Exception Handling Class
@ControllerAdvice(assignableTypes = { BoardController.class, MemberController.class, NoticeController.class })
public class AdviceController {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        // 예외 처리 로직 구현
        return Map.of("Error", ex.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDataNotFoundException(DataNotFoundException ex) {
        // 예외 처리 로직 구현
        return Map.of("Error", ex.getMessage());
    }
}