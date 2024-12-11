import java.util.*;
import java.io.*;

public class b1343 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[] chs;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        for (int i = 0; i < chs.length - 1; i++) {
            System.out.print(chs[i]);
        }
    }

    private static void solve() {
        int stt = 0;
        int end = 0;
        while (end < chs.length) {
            if (chs[end] == '.') {
                int length = end - stt;

                if (length % 2 != 0) {
                    System.out.println(-1);
                    System.exit(0);
                }

                int a = length / 4;
                int b = length % 4;

                for (int i = 0; i < a * 4; i++) {
                    chs[stt + i] = 'A';
                }

                for (int i = 0; i < b; i++) {
                    chs[Math.max(0, end - 1 - i)] = 'B';
                }

                while (stt < chs.length && chs[stt] != 'X') {
                    stt++;
                }

                end = stt - 1;
            }

            end++;
        }

    }

    private static void init() throws IOException {
        char[] temp = br.readLine().toCharArray();
        chs = new char[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            chs[i] = temp[i];
        }
        chs[temp.length] = '.';
    }
}