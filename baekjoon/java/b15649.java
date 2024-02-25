import java.util.*;
import java.io.*;

/**
 * b15649
 */
public class b15649 {
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
        perm(0, 0);
        System.out.println(sb.toString());
    }
    
    private static void perm(int depth, int visited) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(datas[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) != 0) continue;
            datas[depth] = i + 1;
            perm(depth + 1, visited | 1 << i);
        }
    }
}