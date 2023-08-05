package board.file.exception;

// 사용하는 이메일이 이미 있을경우 UserEmailAlreadyExistsException
public class UserEmailAlreadyExistsException extends RuntimeException {

     public UserEmailAlreadyExistsException() {
        super();
    }

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}