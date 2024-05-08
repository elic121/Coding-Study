import java.util.*;
import java.io.*;

public class b2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] level;
    static ArrayList<Integer>[] arr;
    static ArrayDeque<Integer> dq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        while (!dq.isEmpty()) {
            int p = dq.pop();
            System.out.print(p + " ");

            if (!arr[p].isEmpty()) {
                for (int v : arr[p]) {
                    if (--level[v] == 0) {
                        dq.add(v);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (level[i] > 0) {
                System.out.print(i + " ");
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        level = new int[n + 1];
        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            if (!arr[i].isEmpty()) {
                for (int v : arr[i]) {
                    level[v]++;
                }
            }
        }

        dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (level[i] == 0) {
                dq.add(i);
            }
        }
    }
}
