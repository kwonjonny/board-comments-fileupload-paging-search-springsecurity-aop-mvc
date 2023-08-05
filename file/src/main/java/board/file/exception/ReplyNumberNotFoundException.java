package board.file.exception;

// Reply Number 가 없을 때 ReplyNumberNotFoundException
public class ReplyNumberNotFoundException extends RuntimeException {
    public ReplyNumberNotFoundException() {
        super();
    }

    public ReplyNumberNotFoundException(String message) {
        super(message);
    }
}
