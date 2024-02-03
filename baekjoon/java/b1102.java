import java.util.*;
import java.io.*;
/**
 * 1. 가동가능한 발전소 개수 CNT, 비트마스킹에 표시
 * 2. 종료 조건 CNT >= P (처음부터 가동가능한 발전소가 P개보다 많을 수 있음)
 * 3. 반복문을 통해 가동가능한 발전소에서, 가동불가능한 발전소(2중 배열)를 찾아 갱신
 */

public class b1102 {
    static int N, arr[][], dp[][], P, visited, CNT;
    static final int SIZE = 100_000_000_9;
    static StringTokenizer st;
    static char[] command;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1<<N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], SIZE);
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        command = br.readLine().toCharArray();
        P = Integer.parseInt(br.readLine());

        solution();
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            if (command[i] == 'Y') {
                visited |= 1 << i;
                CNT++;
            }
        }
        int res = travel(CNT, visited);
        System.out.println(res == SIZE ? -1 : res);
    }

    private static int travel(int depth, int visited) {
        if (depth >= P) {
            return 0;
        }

        if (dp[depth][visited] != SIZE) {
            return dp[depth][visited];
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1<<i) != 1<<i) continue;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if ((visited & (1 << j)) == 1 << j) continue;
                int dist = arr[i][j] + travel(depth + 1, visited | 1 << j);
                dp[depth][visited] = Math.min(dp[depth][visited], dist);
            }
        }

        return dp[depth][visited];
    }
}
