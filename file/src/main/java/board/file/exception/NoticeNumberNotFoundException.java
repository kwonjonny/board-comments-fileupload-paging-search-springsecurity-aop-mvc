package board.file.exception;

// 공지사항의 번호가 없을 때 NoticeNumberNotFoundException
public class NoticeNumberNotFoundException extends RuntimeException {
    public NoticeNumberNotFoundException() {
        super();
    }

    public NoticeNumberNotFoundException(String message) {
        super(message);
    }
}
