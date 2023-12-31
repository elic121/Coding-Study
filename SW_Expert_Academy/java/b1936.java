import java.io.*;
import java.util.StringTokenizer;

public class b1936 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        char sol;
        if (A>B) {
            if (A == 3 && B == 1) {
                sol = 'B';
            } else {
                sol = 'A';
            }
        } else {
            if (B == 3 && A == 1) {
                sol = 'A';
            } else {
                sol = 'B';
            }
        }
        System.out.println(sol);
    }
}
