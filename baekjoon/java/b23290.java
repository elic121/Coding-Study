import java.util.*;
import java.io.*;

public class b23290 {
    static int M, S;
    static int SX, SY;
    static int[][] smell;
    static final int[] dx = { -1, 0, 1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static final int[] dsx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static final int[] dsy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static ArrayList<Integer>[][] arr, copy;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < S; i++) {
            copy();
            moveFish();
            moveShark();
            removeSmell();
            paste();
        }

        System.out.println(countFish());
    }

    private static void copy() {
        copy = getNewArray();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : arr[i][j])
                    copy[i][j].add(d);
            }
        }
    }

    private static void moveFish() {
        ArrayList<Integer>[][] temp = getNewArray();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : arr[i][j]) {
                    int[] n = nextFish(i, j, d);
                    temp[n[0]][n[1]].add(n[2]);
                }
            }
        }

        arr = temp;
    }

    private static int[] nextFish(int x, int y, int d) {
        for (int i = 0; i < 8; i++) {
            int D = (d - i) + (d < i ? 8 : 0);
            int nx = x + dsx[D];
            int ny = y + dsy[D];
            if (!isRange(nx, ny))
                continue;
            if (smell[nx][ny] > 0)
                continue;
            if (nx == SX && ny == SY)
                continue;
            return new int[] { nx, ny, D };
        }
        return new int[] { x, y, d };
    }

    private static void moveShark() {
        int[] nextD = new int[] { -1, -1, -1 };
        int maxF = -1;

        int x = SX;
        int y = SY;
        for (int i = 0; i < 4; i++) {
            int ix = x + dx[i];
            int iy = y + dy[i];
            if (!isRange(ix, iy))
                continue;

            for (int j = 0; j < 4; j++) {
                int jx = ix + dx[j];
                int jy = iy + dy[j];
                if (!isRange(jx, jy))
                    continue;

                for (int k = 0; k < 4; k++) {
                    int kx = jx + dx[k];
                    int ky = jy + dy[k];
                    if (!isRange(kx, ky))
                        continue;

                    int size = arr[ix][iy].size() + arr[jx][jy].size()
                            + (!(ix == kx && iy == ky) ? arr[kx][ky].size() : 0);

                    if (maxF < size) {
                        maxF = size;
                        nextD = new int[] { i, j, k };
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            SX += dx[nextD[i]];
            SY += dy[nextD[i]];
            if (arr[SX][SY].size() > 0) {
                arr[SX][SY].clear();
                smell[SX][SY] = 3;
            }
        }
    }

    private static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                smell[i][j] += smell[i][j] > 0 ? -1 : 0;
            }
        }
    }

    private static void paste() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : copy[i][j])
                    arr[i][j].add(d);
            }
        }
    }

    private static int countFish() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cnt += arr[i][j].size();
            }
        }
        return cnt;
    }

    private static ArrayList<Integer>[][] getNewArray() {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[][] temp = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }

        return temp;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        smell = new int[4][4];

        arr = getNewArray();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr[fx - 1][fy - 1].add(d - 1);
        }

        st = new StringTokenizer(br.readLine());
        SX = Integer.parseInt(st.nextToken()) - 1;
        SY = Integer.parseInt(st.nextToken()) - 1;
    }
}
