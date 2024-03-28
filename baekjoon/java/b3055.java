import java.util.*;
import java.io.*;

/**
 * 11880kb
 * 92ms
 */
public class b3055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static int R, C, SX, SY;
    static int[][] water;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { SX, SY });

        boolean[][] visited = new boolean[R][C];
        visited[SX][SY] = true;

        int CNT = 0;
        while (!dq.isEmpty()) {
            flow(++CNT);

            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int[] pos = dq.pop();
                for (int k = 0; k < 4; k++) {
                    int nx = pos[0] + dx[k];
                    int ny = pos[1] + dy[k];

                    if (!isValid(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (arr[nx][ny] == 'D') {
                        System.out.println(CNT);
                        return;
                    }

                    dq.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        System.out.println("KAKTUS");
    }

    private static void flow(int v) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (water[i][j] != v) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny) || arr[nx][ny] == 'D') {
                        continue;
                    }
                    water[nx][ny] = v + 1;
                }
            }
        }
    }

    private static boolean isValid(int nx, int ny) {
        if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
            return false;
        }
        if (water[nx][ny] > 0) {
            return false;
        }
        if (arr[nx][ny] == 'X') {
            return false;
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        water = new int[R][C];
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'S') {
                    SX = i;
                    SY = j;
                } else if (arr[i][j] == '*') {
                    water[i][j] = 1;
                }
            }
        }
    }

}
