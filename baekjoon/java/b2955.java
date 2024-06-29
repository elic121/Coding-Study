import java.util.*;
import java.io.*;

public class b2955 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int n = 9;
    static int[] hor;
    static int[] ver;
    static int[][] box;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        while (judge()) {
        }
    }

    private static boolean judge() {
        boolean isChanged = false;

        for (int r = 1; r <= n; r++) {
            for (int b = 0; b < n; b++) {
                int x = b / 3;
                int y = b % 3;
                if ((box[x][y] & 1 << r) != 0) {
                    continue;
                }
                int nx = 3 * x;
                int ny = 3 * y;

                int cnt = 0;
                int rx = -1;
                int ry = -1;
                end: for (int i = nx; i < nx + 3; i++) {
                    for (int j = ny; j < ny + 3; j++) {
                        if (arr[i][j] != 0) {
                            continue;
                        }

                        if (check(i, j, r)) {
                            cnt++;
                            rx = i;
                            ry = j;
                        }

                        if (cnt >= 2) {
                            break end;
                        }
                    }
                }

                if (cnt == 0) {
                    System.out.println("ERROR");
                    System.exit(0);
                }

                if (cnt == 1) {
                    fill(rx, ry, r);
                    isChanged = true;
                }
            }
        }

        return isChanged;
    }

    private static void fill(int x, int y, int num) {
        hor[x] |= 1 << num;
        ver[y] |= 1 << num;
        box[x / 3][y / 3] |= 1 << num;
        arr[x][y] = num;
    }

    private static boolean check(int x, int y, int num) {
        if ((hor[x] & 1 << num) != 0) {
            return false;
        }

        if ((ver[y] & 1 << num) != 0) {
            return false;
        }

        if ((box[x / 3][y / 3] & 1 << num) != 0) {
            return false;
        }

        return true;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j] == 0 ? "." : arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void init() throws IOException {
        hor = new int[n];
        ver = new int[n];
        box = new int[3][3];

        boolean inValid = false;
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                if (str.charAt(j) == '.') {
                    arr[i][j] = 0;
                } else {
                    int num = Character.getNumericValue(str.charAt(j));

                    if (check(i, j, num)) {
                        fill(i, j, num);
                    } else {
                        inValid = true;
                    }
                }
            }
        }

        if (inValid) {
            System.out.println("ERROR");
            System.exit(0);
        }
    }
}
