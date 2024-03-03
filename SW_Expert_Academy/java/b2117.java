import java.io.*;
import java.util.*;

public class b2117 {

    static int T, N, M, MAX, arr[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve(tc);
        }
    }

    private static void solve(int tc) {
        for (int size = 1; size <= N + 1; size++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    simulate(i, j, size);
                }
            }
        }
        System.out.printf("#%d %d\n", tc, MAX);
    }

    private static void simulate(int x, int y, int size) {
        int C = cost(size);
        int h = 0;
        for (int i = x - size + 1; i < x + size; i++) {
            for (int j = y - size + 1; j < y + size; j++) {
                if (!isRange(i, j)) continue;
                if (distance(x, y, i, j) >= size) continue;
                h += arr[i][j];
            }
        }

        if (h * M - C >= 0) {
            MAX = Math.max(MAX, h);
        }
    }


    private static int cost(int size) {
        return size * size + (size - 1) * (size - 1);
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        MAX = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}