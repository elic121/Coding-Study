import java.util.*;
import java.io.*;

public class b27110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sol = 0;
        for (int i = 0; i < 3; i++) {
            sol += (Math.min(N, Integer.parseInt(st.nextToken())));
        }
        System.out.println(sol);
    }    
}
