package BookPractice;

public class ISBNNotFoundException extends RuntimeException {
    String isbn;

    public ISBNNotFoundException(String isbn) {
        super(isbn+"을 찾을 수 없습니다.");
    }

    public String getIsbn() {
        return isbn;
    }
}
