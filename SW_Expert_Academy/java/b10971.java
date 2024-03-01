/**
 * 	12612kb
 * 	88ms
 */
import java.util.*;
import java.io.*;

public class b10971 {
    static int N, STT, arr[][], dp[][];
    static final int INF = 11_000_001;
    static final int INIT = 2 * 11_000_001 + 1;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(travel(STT, 1 << STT));
    }
    
    private static int travel(int index, int visited) {
        if (visited == (1 << N) - 1) {
            return arr[index][0] != 0 ? arr[index][0] : INF;
        }

        if (dp[index][visited] != INIT) {
            return dp[index][visited];
        }

        for (int i = 0; i < N; i++) {
            if (arr[index][i] == 0) continue;
            if ((visited & 1 << i) != 0) continue;
            dp[index][visited] = Math.min(dp[index][visited], arr[index][i] + travel(i, visited | 1 << i));
        }

        return dp[index][visited];
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INIT);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        STT = 0;
        dp[STT][STT] = 0;
    }
}
