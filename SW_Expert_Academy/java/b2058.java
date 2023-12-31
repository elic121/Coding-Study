import java.io.*;

public class b2058 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();
        int val = 0;
        for (int i = 0; i < tmp.length(); i++) {
            val += Character.digit(tmp.charAt(i),10);
        }
        System.out.println(val);
    }
}