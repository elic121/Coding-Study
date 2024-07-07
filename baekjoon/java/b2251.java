import java.util.*;
import java.io.*;

public class b2251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int a;
    static int b;
    static int c;
    static int[] total;
    static boolean[] possible;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= c; i++) {
            if (possible[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.print(sb.toString());
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { 0, 0, c, 2 });

        boolean[][][][] visited = new boolean[a + 1][b + 1][c + 1][3];
        visited[0][0][c][2] = true;

        while (!dq.isEmpty()) {
            int[] p = dq.pop();

            if (p[0] == 0) {
                possible[p[2]] = true;
            }

            for (int i = 0; i < 3; i++) {
                if (i == p[3]) {
                    continue;
                }

                int[] r = new int[] { p[0], p[1], p[2] };
                int rest = total[i] - r[i];

                r[i] = rest > r[p[3]] ? r[i] + r[p[3]] : total[i];
                r[p[3]] = Math.max(0, r[p[3]] - rest);

                for (int j = 0; j < 3; j++) {
                    if (visited[r[0]][r[1]][r[2]][j]) {
                        continue;
                    }

                    if (r[j] == 0) {
                        continue;
                    }

                    dq.add(new int[] { r[0], r[1], r[2], j });
                    visited[r[0]][r[1]][r[2]][j] = true;
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        total = new int[] { a, b, c };

        possible = new boolean[c + 1];
    }
}