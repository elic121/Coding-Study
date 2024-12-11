import java.util.*;
import java.io.*;

public class b2578 {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] nums;
    static int[] rows;
    static int[] cols;
    static int[] cross;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 1; i <= 25; i++) {
            int num = nums[i];

            Point point = points[num];
            int x = point.x;
            int y = point.y;

            rows[x] |= 1 << y;
            cols[y] |= 1 << x;

            if (x == y) {
                cross[0] |= 1 << x;

                if (x == 2) {
                    cross[1] |= 1 << x;
                }
            }

            if (x + y == 4 && x != 2) {
                cross[1] |= 1 << y;
            }

            if (check() >= 3) {
                System.out.println(i);
                System.exit(0);
            }
        }
    }

    private static int check() {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (rows[i] == (1 << 5) - 1) {
                cnt++;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (cols[i] == (1 << 5) - 1) {
                cnt++;
            }
        }

        for (int i = 0; i < 2; i++) {
            if (cross[i] == (1 << 5) - 1) {
                cnt++;
            }
        }
        return cnt;
    }

    private static void init() throws IOException {
        points = new Point[26];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                points[num] = new Point(i, j);
            }
        }

        nums = new int[26];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nums[5 * i + j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        rows = new int[5];
        cols = new int[5];
        cross = new int[2];
    }
}