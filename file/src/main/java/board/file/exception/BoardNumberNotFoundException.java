package board.file.exception;

// 해당하는 게시물 번호가 없을 때 BoardNumberNotFoundException
public class BoardNumberNotFoundException extends RuntimeException {
    public BoardNumberNotFoundException() {
        super();
    }

    public BoardNumberNotFoundException(String message) {
        super(message);
    }
}
