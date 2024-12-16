import java.util.*;
import java.io.*;

public class b16198 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int max;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(max);
    }

    private static void solve() {
        perm(0, 0, 0);
    }

    private static void perm(int depth, int visited, int weight) {
        if (depth == n - 2) {
            max = Math.max(max, weight);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            int left = -1;
            int right = -1;

            for (int x = i - 1; x >= 0; x--) {
                if ((visited & 1 << x) == 0) {
                    left = x;
                    break;
                }
            }

            for (int x = i + 1; x < n; x++) {
                if ((visited & 1 << x) == 0) {
                    right = x;
                    break;
                }
            }

            perm(depth + 1, visited | 1 << i, weight + arr[left] * arr[right]);
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        max = 0;

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
