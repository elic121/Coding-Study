import java.util.*;
import java.io.*;

public class b17286 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double getDistance(Pair p) {
            int diffX = (this.x - p.x);
            int diffY = (this.y - p.y);
            return Math.sqrt(diffX * diffX + diffY * diffY);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static double min;
    static Pair yumi;
    static Pair[] pairs;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println((int) min);
    }

    private static void solve() {
        perm(0, 0, yumi, 0.0);
    }

    private static void perm(int depth, int visited, Pair prev, double dist) {
        if (depth == 3) {
            min = Math.min(min, dist);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            perm(depth + 1, visited | 1 << i, pairs[i], dist + pairs[i].getDistance(prev));
        }
    }

    private static void init() throws IOException {
        min = Double.MAX_VALUE;
        pairs = new Pair[3];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        yumi = new Pair(x, y);

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(x, y);
        }
    }
}