package board.file.exception;

// 이메일이 올바른 형식이 아닐시 InvalidEmailException
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super();
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
