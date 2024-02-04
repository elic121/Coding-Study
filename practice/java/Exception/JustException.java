package Exception;

public class JustException extends RuntimeException {
    public JustException() {
        super("그냥... 오류낼게");
    }

    public JustException(String msg) {
        super(msg);
    }

}
