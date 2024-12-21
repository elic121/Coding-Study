import java.util.*;
import java.io.*;

public class b5555 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int cnt;
    static char[] pivot;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(cnt);
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            cnt += isValid(i) ? 1 : 0;
        }
    }

    private static boolean isValid(int index) {
        char initial = pivot[0];

        end: for (int i = 0; i < 10; i++) {
            if (arr[index][i] == initial) {
                for (int j = 1; j < pivot.length; j++) {
                    if (arr[index][(i + j) % 10] != pivot[j]) {
                        continue end;
                    }
                }
                return true;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        cnt = 0;
        pivot = br.readLine().toCharArray();

        n = Integer.parseInt(br.readLine());

        arr = new char[n][10];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}