import java.util.*;
import java.io.*;

public class b16940 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer>[] arr;
    static int n;
    static int idx;
    static int[] lst;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(1);

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        check[1] = true;

        while (!dq.isEmpty()) {
            int size = dq.size();

            for (int i = 0; i < size; i++) {
                int p = dq.pop();

                int cnt = 0;
                if (!arr[p].isEmpty()) {
                    for (int v : arr[p]) {
                        if (visited[v]) {
                            continue;
                        }
                        check[v] = true;
                        cnt++;
                    }
                }

                int stt = idx;
                if (!isValid(cnt)) {
                    System.out.println(0);
                    return;
                }
                int end = idx;

                for (int j = stt; j < end; j++) {
                    dq.add(lst[j]);
                    visited[lst[j]] = true;
                }
            }
        }
        System.out.println(idx == n + 1 ? 1 : 0);
    }

    private static boolean isValid(int size) {
        for (int i = 0; i < size; i++) {
            if (!check[lst[idx++]]) {
                return false;
            }
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        idx = 2;
        lst = new int[n + 1];
        check = new boolean[n + 1];

        arr = new ArrayList[n + 1];
        for (int i = 0; i < 1 + n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            lst[i] = Integer.parseInt(st.nextToken());
        }

        if (lst[1] != 1) {
            System.out.println(0);
            System.exit(0);
        }
    }
}