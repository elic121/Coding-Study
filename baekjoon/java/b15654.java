import java.util.*;
import java.io.*;

/**
 * b15654
 */
public class b15654 {
    static int N, M, datas[], arr[];
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        datas = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            datas[i] = Integer.parseInt(st.nextToken());    
        }
        
        Arrays.sort(datas);

        sb = new StringBuilder();
        perm(0, 0);
        System.out.println(sb.toString());
    }
    
    private static void perm(int depth, int visited) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) != 0) continue;
            arr[depth] = datas[i];
            perm(depth + 1, visited | 1 << i);
        }
    }
}