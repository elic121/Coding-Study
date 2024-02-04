package Exception;

public class ExceptionTest {
    public static void main(String[] args) {
        ExceptionFunc f = new ExceptionFunc();
        
        try {
            f.except();
        } catch (JustException e){
            e.printStackTrace();
        }
    }
}
