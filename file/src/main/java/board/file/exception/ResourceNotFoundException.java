package board.file.exception;

// 해당하는 리소스가 없을 때 ResourceNotFoundException
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
