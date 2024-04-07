package codetree.java;

import java.util.*;
import java.io.*;

public class 나무박멸 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dsx = { -1, -1, 1, 1 };
    static final int[] dsy = { -1, 1, -1, 1 };
    static int n;
    static int m;
    static int k;
    static int c;
    static int elem;
    static int[][] arr;
    static int[][] ip;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 1; i <= m; i++) {
            simulate();
        }
        System.out.println(elem);
    }

    private static void simulate() {
        revive();
        grow();
        breed();
        spread();
    }

    private static void revive() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ip[i][j] = Math.max(ip[i][j] - 1, 0);
            }
        }
    }

    private static void grow() {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] += arr[i][j];
                if (ip[i][j] > 0 || arr[i][j] <= 0) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (isRange(nx, ny) && arr[nx][ny] > 0) {
                        temp[i][j]++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(temp[i], n);
        }
    }

    private static void breed() {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] += arr[i][j];
                if (ip[i][j] > 0 || arr[i][j] <= 0) {
                    continue;
                }

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (isRange(nx, ny) && arr[nx][ny] == 0 && ip[nx][ny] == 0) {
                        cnt++;
                    }
                }

                if (cnt > 0) {
                    int val = arr[i][j] / cnt;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (isRange(nx, ny) && arr[nx][ny] == 0 && ip[nx][ny] == 0) {
                            temp[nx][ny] += val;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(temp[i], n);
        }
    }

    private static void spread() {
        int x = -1;
        int y = -1;
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = Math.max(arr[i][j], 0);
                if (max < cnt) {
                    x = i;
                    y = j;
                    max = cnt;
                }

                if (arr[i][j] <= 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    for (int s = 1; s <= k; s++) {
                        int nx = i + s * dsx[d];
                        int ny = j + s * dsy[d];
                        if (!isRange(nx, ny)) {
                            break;
                        }
                        cnt += Math.max(arr[nx][ny], 0);
                        if (arr[nx][ny] <= 0) {
                            break;
                        }
                    }
                }
                if (max < cnt) {
                    x = i;
                    y = j;
                    max = cnt;
                }
            }
        }

        elem += Math.max(arr[x][y], 0);
        arr[x][y] = Math.min(arr[x][y], 0);
        ip[x][y] = c;
        for (int d = 0; d < 4; d++) {
            for (int s = 1; s <= k; s++) {
                int nx = x + s * dsx[d];
                int ny = y + s * dsy[d];
                if (!isRange(nx, ny)) {
                    break;
                }
                ip[nx][ny] = c;
                elem += Math.max(arr[nx][ny], 0);
                if (arr[nx][ny] <= 0) {
                    break;
                }
                arr[nx][ny] = Math.min(arr[nx][ny], 0);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken()) + 1;

        elem = 0;
        ip = new int[n][n];
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}