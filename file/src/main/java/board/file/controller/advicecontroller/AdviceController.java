package board.file.controller.advicecontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import board.file.controller.BoardController;
import board.file.controller.FileUploadController;
import board.file.controller.LikeController;
import board.file.controller.MemberController;
import board.file.controller.NoticeController;
import board.file.controller.ReplyController;
import board.file.controller.ReplyController.DataNotFoundException;

@ControllerAdvice(assignableTypes = { BoardController.class, MemberController.class, NoticeController.class })
@RestControllerAdvice(assignableTypes = { FileUploadController.class, LikeController.class, ReplyController.class })
public class AdviceController {

	// 처리할 예외: MethodArgumentTypeMismatchException
	// 발생 원인: 요청에 대한 메서드의 인자가 올바르지 않은 경우
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return generateErrorMap(ex, "Method Argument Type Mismatch");
	}

	// 처리할 예외: DataNotFoundException
	// 발생 원인: 필요한 데이터를 찾을 수 없는 경우
	// HTTP 상태 코드: 404 (Not Found)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleDataNotFoundException(DataNotFoundException ex) {
		return generateErrorMap(ex, "Data Not Found");
	}

	// 처리할 예외: BindException
	// 발생 원인: 바인딩에 실패한 경우, 즉, 폼 검증에서 오류가 발생한 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler(BindException.class)
	@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
		Map<String, String> errorMap = new HashMap<>();
		if (ex.hasErrors()) {
			BindingResult bindingResult = ex.getBindingResult();

			bindingResult.getFieldErrors().forEach(fieldError -> {
				errorMap.put(fieldError.getField(), fieldError.getCode());
			});
		}
		return generateErrorEntity(errorMap);
	}

	// 처리할 예외: DataIntegrityViolationException
	// 발생 원인: 데이터 무결성 위반, 즉, SQL 제약 조건을 위반하는 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> handleFKException(Exception ex) {
		return generateErrorEntity(generateErrorMap(ex, "constraint fail"));
	}

	// 처리할 예외: NoSuchElementException, EmptyResultDataAccessException
	// 발생 원인: 요청한 엔티티가 없는 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler({
			NoSuchElementException.class,
			EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception ex) {
		return generateErrorEntity(generateErrorMap(ex, "No Such Element Exception"));
	}

	// 처리할 예외: NullPointerException
	// 발생 원인: 요청한 데이터가 없는 경우
	// HTTP 상태 코드 : 500(Null Pointer Exception)
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Null Pointer Exception"));
	}

	// 처리할 예외: IllegalArgumentException
	// 발생 원인: 메서드에 잘못된 인자를 전달한 경우
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Illegal Argument Exception"));
	}

	// 처리할 예외: ArrayIndexOutOfBoundsException
	// 발생 원인: 배열에 없는 인덱스를 참조하려고 할 때 발생
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Array Index Out Of Bounds Exception"));
	}

	// 처리할 예외: NumberFormatException
	// 발생 원인: 숫자로 파싱할 수 없는 문자열을 숫자로 변환하려고 할 때
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleNumberFormatException(NumberFormatException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Number Format Exception"));
	}

	// 처리할 예외: HttpMessageNotReadableException
	// 발생 원인: HTTP 요청의 본문을 객체로 변환할 수 없을 때 (Spring에서 사용)
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex) {
		return generateErrorEntity(generateErrorMap(ex, "HTTP Message Not Readable Exception"));
	}

	// 처리할 예외: HttpClientErrorException
	// 발생 원인: HTTP 상태 코드 4xx를 반환하는 HTTP 응답을 받았을 때 발생
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleHttpClientErrorException(HttpClientErrorException ex) {
		return generateErrorEntity(generateErrorMap(ex, "HTTP Client Error Exception"));
	}

	// 처리할 예외: HttpServerErrorException
	// 발생 원인: HTTP 상태 코드 5xx를 반환하는 HTTP 응답을 받았을 때 발생
	// HTTP 상태 코드: 500 (Internal Server Error)
	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Map<String, String>> handleHttpServerErrorException(HttpServerErrorException ex) {
		return generateErrorEntity(generateErrorMap(ex, "HTTP Server Error Exception"));
	}

	// 처리할 예외: ResourceNotFoundException
	// 발생 원인: 요청한 리소스를 찾을 수 없을 때
	// HTTP 상태 코드: 404 (Not Found)
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Resource Not Found Exception"));
	}

	public static class ResourceNotFoundException extends RuntimeException {
		public ResourceNotFoundException(String message) {
			super(message);
		}
	}

	// 처리할 예외: AccessDeniedException
	// 발생 원인: 요청한 자원에 대한 권한이 없을 때
	// HTTP 상태 코드: 403 (Forbidden)
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
		return generateErrorEntity(generateErrorMap(ex, "Access Denied Exception"));
	}

	// generateErrorMap: 공통적으로 사용되는 에러 메시지와 현재 시간을 맵에 담아 반환하는 메서드
	private Map<String, String> generateErrorMap(Exception ex, String msg) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("time", "" + System.currentTimeMillis());
		errorMap.put("msg", msg);
		return errorMap;
	}

	// generateErrorEntity: 공통적으로 사용되는 에러 메시지를 담은 ResponseEntity를 생성하는 메서드
	private ResponseEntity<Map<String, String>> generateErrorEntity(Map<String, String> errorMap) {
		return ResponseEntity.badRequest().body(errorMap);
	}
}