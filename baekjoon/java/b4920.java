import java.util.*;
import java.io.*;

public class b4920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int iter = 0;
    static int n;
    static int max;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        for (;;) {
            init();
            solve();
            print();
        }
    }

    private static void solve() {
        simulate();
    }

    private static void simulate() {
        for (int r = 0; r < 4; r++) {
            for (int i = 0; i < n - 3 + 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    int sum = arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j + 1];
                    max = Math.max(max, sum);

                    sum = sum + arr[i][j + 1] - arr[i + 1][j + 1];
                    max = Math.max(max, sum);

                    sum = sum + arr[i + 1][j + 1] - arr[i][j];
                    max = Math.max(max, sum);
                }
            }

            for (int i = 0; i < n - 4 + 1; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0;
                    for (int s = 0; s < 4; s++) {
                        sum += arr[i + s][j];
                    }
                    max = Math.max(max, sum);
                }
            }

            rotate();
        }

        for (int i = 0; i < n - 2 + 1; i++) {
            for (int j = 0; j < n - 2 + 1; j++) {
                int sum = arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i + 1][j + 1];
                max = Math.max(max, sum);
            }
        }
    }

    private static void rotate() {
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = arr[n - 1 - j][i];
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(temp[i], n);
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();

        sb.append(iter).append(". ").append(max);
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine().split(" ")[0]);
        if (n == 0) {
            System.exit(0);
        }

        iter++;
        max = -4_000_001;

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}