import java.util.*;
import java.io.*;

public class b17071 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int min;
    static int[] dist;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min = 987654321;

        init();
        solve();
    }

    private static void init() {
        dist = new int[500001];
        Arrays.fill(dist, 987654321);

        int pos = k;
        int key = 0;
        while (pos <= 500000) {
            dist[pos] = key;
            key++;
            pos += key;
        }
    }

    private static void solve() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { n, 0 });

        visited = new boolean[500001][2];
        visited[n][0] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (dist[p[0]] != 987654321 && dist[p[0]] >= p[1] && (dist[p[0]] - p[1]) % 2 == 0) {
                min = Math.min(min, dist[p[0]]);
            }

            for (int i = 0; i < 3; i++) {
                int next = next(i, p[0]);

                if (next < 0 || next > 500000) {
                    continue;
                }

                if (visited[next][(p[1] + 1) % 2]) {
                    continue;
                }

                dq.add(new int[] { next, p[1] + 1 });
                visited[next][(p[1] + 1) % 2] = true;
            }
        }
        System.out.println(min == 987654321 ? -1 : min);
    }

    private static int next(int idx, int num) {
        if (idx == 0) {
            return num << 1;
        } else if (idx == 1) {
            return ++num;
        } else {
            return --num;
        }
    }
}
