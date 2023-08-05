package board.file.exception;

// 유저가 없을때 UserNotFoundException
public class UserNotFoundException extends RuntimeException {
    
     public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
