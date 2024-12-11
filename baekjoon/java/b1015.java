import java.util.*;
import java.io.*;

public class b1015 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int cnt;
    static int[] arr;
    static int[] ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb.toString());
    }

    private static void solve() {
        while (true) {
            int checker = check();

            if (checker == -1) {
                break;
            } else {
                ans[checker] = cnt++;
                visited[checker] = true;
            }
        }
    }

    private static int check() {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }

            if (min >= arr[i]) {
                min = arr[i];
                index = i;
            }
        }

        return index;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = 0;

        arr = new int[n];
        ans = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

    }
}