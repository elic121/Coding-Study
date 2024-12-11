import java.util.*;
import java.io.*;

public class b10973 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (!prevPermutation()) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(seq[i]);
                sb.append(" ");
            }

            System.out.print(sb.toString());
        }
    }

    private static boolean prevPermutation() {
        int i = n - 2;

        while (i >= 0 && seq[i] < seq[i + 1]) {
            i--;
        }

        if (i == -1) {
            return false;
        }

        int j = n - 1;
        while (seq[j] > seq[i]) {
            j--;
        }

        swap(i++, j--);
        reverse(i, n - 1);

        return true;
    }

    private static void reverse(int x, int y) {
        while (x < y) {
            swap(x, y);
            x++;
            y--;
        }
    }

    private static void swap(int i, int j) {
        int temp = seq[i];
        seq[i] = seq[j];
        seq[j] = temp;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        seq = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
    }
}