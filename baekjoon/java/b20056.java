import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class b20056 {
    static class Fire {
        int m, s, d;
        Fire(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N, M, K;
    static ArrayList<Fire>[][] arr, temp, copy;
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < K; i++) {
            moveAndCombine();
        }
        countFire();
    }

    private static void moveAndCombine() {
        temp = getNewList();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fire f : arr[i][j]) {
                    int x = i + f.s * dx[f.d];
                    int y = j + f.s * dy[f.d];
                    int nx = (x + N * (x < 0 ? (int)Math.ceil(-(double)x / N) : 0)) % N;
                    int ny = (y + N * (y < 0 ? (int)Math.ceil(-(double)y / N) : 0)) % N;
                    temp[nx][ny].add(f);
                }
            }
        }

        copy = getNewList();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j].isEmpty()) continue;
                make(i, j);
            }
        }

        arr = getNewList();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j].isEmpty()) continue;
                for (Fire f : copy[i][j]) arr[i][j].add(f);
            }
        }
    }

    private static void make(int x, int y) {
        int size = temp[x][y].size();
        if (size == 1) {
            copy[x][y].add(temp[x][y].get(0));
            return;
        }

        int newM = 0;
        int newS = 0;
        int newD = -1;
        int O = 0;
        int E = 0;
        for (Fire f : temp[x][y]) {
            newM += f.m;
            newS += f.s;
            O += f.d % 2 == 1 ? 1 : 0;
            E += f.d % 2 == 0 ? 1 : 0;
        }

        newM /= 5;
        if (newM == 0) return;

        newS /= size;
        newD = (O == size || E == size) ? 0 : 1;
        
        for (int i = newD; i < 8; i += 2) {
            copy[x][y].add(new Fire(newM, newS, i));
        }
    }

    private static void countFire() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fire f : arr[i][j]) {
                    cnt += f.m;
                }
            }
        }
        System.out.println(cnt);
    }

    private static ArrayList<Fire>[][] getNewList() {
        ArrayList<Fire>[][] copy = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = new ArrayList<>();
            }
        }
        return copy;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = getNewList();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[r][c].add(new Fire(m, s, d));
        }
    }
}
