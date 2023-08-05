package board.file.exception;

// 해당하는 데이터가 없을때 DataNotFoundException
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
