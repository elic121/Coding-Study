import java.util.*;
import java.io.*;

public class b2096 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] arr;
    static int[][] dpMax;
    static int[][] dpMin;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = -1; k <= 1; k++) {
                    int idx = j + k;
                    if (isRange(idx)) {
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i - 1][idx]);
                        dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i - 1][idx]);
                    }
                }
                dpMax[i][j] += arr[i][j];
                dpMin[i][j] += arr[i][j];
            }
        }

        int max = Math.max(dpMax[n][2], Math.max(dpMax[n][0], dpMax[n][1]));
        int min = Math.min(dpMin[n][2], Math.min(dpMin[n][0], dpMin[n][1]));
        System.out.println(max + " " + min);
    }

    private static boolean isRange(int x) {
        return 0 <= x && x < 3;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][3];
        dpMax = new int[n + 1][3];
        dpMin = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dpMin[i][j] = 999999;
            }
        }

        for (int i = 0; i < 3; i++) {
            dpMax[1][i] = arr[1][i];
            dpMin[1][i] = arr[1][i];
        }
    }
}