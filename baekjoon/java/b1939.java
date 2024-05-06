import java.util.*;
import java.io.*;

public class b1939 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int stt;
    static int end;
    static int lo;
    static int hi;
    static boolean[] visited;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (bfs(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        System.out.println(lo + (bfs(lo) ? 0 : -1));
    }

    private static boolean bfs(int k) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(stt);

        visited = new boolean[10001];
        visited[stt] = true;

        while (!dq.isEmpty()) {
            int p = dq.pop();
            if (p == end) {
                return true;
            }
            for (int[] info : arr[p]) {
                if (!visited[info[0]] && info[1] >= k) {
                    dq.add(info[0]);
                    visited[info[0]] = true;
                }
            }
        }
        return false;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[10001];
        for (int i = 0; i < 10001; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new int[] { b, c });
            arr[b].add(new int[] { a, c });
            hi = Math.max(hi, c);
        }

        st = new StringTokenizer(br.readLine());
        stt = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}
