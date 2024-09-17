import java.util.*;
import java.io.*;

public class b18428 {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static ArrayList<Point> teachers;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        dfs(0, 0);
        System.out.println("NO");
    }

    private static void dfs(int depth, int curr) {
        if (depth == 3) {
            if (isValid()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int d = curr; d < n * n; d++) {
            int x = d / n;
            int y = d % n;

            if (arr[x][y] == 'S') {
                continue;
            }
            if (arr[x][y] == 'T') {
                continue;
            }
            if (arr[x][y] == 'X') {
                arr[x][y] = 'O';
                dfs(depth + 1, d + 1);
                arr[x][y] = 'X';
            }
        }
    }

    private static boolean isValid() {
        for (Point p : teachers) {
            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < n; i++) {
                    int nx = p.x + i * dx[d];
                    int ny = p.y + i * dy[d];

                    if (!isRange(nx, ny)) {
                        break;
                    }

                    if (arr[nx][ny] == 'O') {
                        break;
                    }

                    if (arr[nx][ny] == 'S') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        teachers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = ch[j << 1];

                if (arr[i][j] == 'T') {
                    teachers.add(new Point(i, j));
                }
            }
        }
    }
}