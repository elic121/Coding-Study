import java.util.*;
import java.io.*;

public class b1652 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int hor;
    static int ver;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(hor + " " + ver);
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            int cnt = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '.') {
                    cnt++;
                } else {
                    hor += cnt >= 2 ? 1 : 0;
                    cnt = 0;
                }
            }

            if (cnt >= 2) {
                hor++;
            }

            cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == '.') {
                    cnt++;
                } else {
                    ver += cnt >= 2 ? 1 : 0;
                    cnt = 0;
                }
            }

            if (cnt >= 2) {
                ver++;
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        hor = 0;
        ver = 0;

        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}