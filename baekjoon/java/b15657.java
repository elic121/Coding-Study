import java.util.*;
import java.io.*;

/**
 * b15657
 */
public class b15657 {
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
        comb(0, 0);
        System.out.println(sb.toString());
    }
    
    private static void comb(int depth, int index) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < N; i++) {
            arr[depth] = datas[i];
            comb(depth + 1, i);
        }
    }
}
