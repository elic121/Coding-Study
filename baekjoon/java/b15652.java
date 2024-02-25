import java.util.*;
import java.io.*;

/**
 * b15652
 */
public class b15652 {
    static int N, M, datas[];
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        datas = new int[N];
        comb(0, 0);
        System.out.println(sb.toString());
    }
    
    private static void comb(int depth, int index) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(datas[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < N; i++) {
            datas[depth] = i + 1;
            comb(depth + 1, i);
        }
    }
}
