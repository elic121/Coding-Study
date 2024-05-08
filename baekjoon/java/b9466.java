import java.util.*;
import java.io.*;

public class b9466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static int cnt;
    static int[] arr;
    static boolean[] temp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            init();
            solve();
        }
    }

    private static void solve() {
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, temp);
            }
        }

        System.out.println(n - cnt);
    }

    private static void dfs(int curr, boolean[] temp) {
        if (visited[curr]) {
            return;
        }

        if (temp[curr]) {
            visited[curr] = true;
            cnt++;
        }

        temp[curr] = true;
        dfs(arr[curr], temp);
        visited[curr] = true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        cnt = 0;
        arr = new int[n + 1];
        temp = new boolean[n + 1];
        visited = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}