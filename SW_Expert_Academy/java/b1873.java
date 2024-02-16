import java.util.*;
import java.io.*;

public class b1873 {

    static int T, H, W, N, x, y, dir;
    static char[] command;
    static char[][] arr;
    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };
    static final char[] CH = { '^', 'v', '<', '>' };
    static final char[] COM = { 'U', 'D', 'L', 'R' };
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            x = y = dir = -1;

            arr = new char[H][W];
            for (int i = 0; i < H; i++) {
                arr[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    char ch = arr[i][j];
                    if (ch == '<' || ch == '>' || ch == '^' || ch == 'v') {
                        x = i;
                        y = j;
                        dir = getDirection(ch);
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            command = new char[N];
            command = br.readLine().toCharArray();

            solve(tc);

        }
    }

    private static void solve(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(index).append(' ');

        for (int i = 0; i < N; i++) {
            simulate(command[i]);
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(arr[i][j]);
            }
            if (i != H - 1) sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private static void simulate(char ch) {
        if (ch == 'S') shoot();
        else move(ch);
    }

    private static void move(char ch) {
        dir = getDirection(ch);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!isRange(nx, ny) || arr[nx][ny] == '*' || arr[nx][ny] == '#' || arr[nx][ny] == '-') {
            arr[x][y] = CH[dir];
            return;
        }

        arr[x][y] = '.';
        x = nx;
        y = ny;
        arr[x][y] = CH[dir];
    }

    private static void shoot() {
        int cx = x;
        int cy = y;
        while (isRange(cx, cy) && arr[cx][cy] != '*' && arr[cx][cy] != '#') {
            cx += dx[dir];
            cy += dy[dir];
        }

        if (!isRange(cx, cy))
            return;

        if (arr[cx][cy] == '*') {
            arr[cx][cy] = '.';
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    private static int getDirection(char ch) {
        for (int i = 0; i < 4; i++) {
            if (ch == CH[i])
                return i;
            if (ch == COM[i])
                return i;
        }
        return -1;
    }
}
