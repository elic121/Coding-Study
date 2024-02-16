import java.io.*;

public class b1992 {

    static int N;
    static char[][] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        solve();
    }

    private static void solve() {
        divideAndConquer(0, 0, N);
        System.out.println(sb.toString());
    }

    private static void divideAndConquer(int x, int y, int size) {
        if (check(x, y, size)) {
            sb.append(arr[x][y]);
            return;
        }

        size >>>= 1;
        sb.append('(');
        for (int i = 0; i < 4; i++) {
            divideAndConquer(x + size * (i >>> 1), y + size * (i % 2), size);
        }
        sb.append(')');
    }

    private static boolean check(int x, int y, int size) {
        char pivot = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != pivot) return false;
            }
        }
        return true;
    }
}
