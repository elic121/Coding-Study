import java.util.*;
import java.io.*;

public class b2864 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[] n;
    static char[] m;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        printValue(false);
        printValue(true);
    }

    private static void printValue(boolean isMax) {
        convert(n, isMax);
        convert(m, isMax);
        print();
    }

    private static void print() {
        int a = getNumber(n);
        int b = getNumber(m);

        System.out.print(a + b + " ");
    }

    private static int getNumber(char[] chs) {
        int pivot = 1;
        int value = 0;

        for (int i = chs.length - 1; i >= 0; i--) {
            value += pivot * Character.getNumericValue(chs[i]);
            pivot *= 10;
        }

        return value;
    }

    private static void convert(char[] chs, boolean isMax) {
        for (int i = 0; i < chs.length; i++) {
            if (isMax && chs[i] == '5') {
                chs[i] = '6';
            }

            if (!isMax && chs[i] == '6') {
                chs[i] = '5';
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = st.nextToken().toCharArray();
        m = st.nextToken().toCharArray();
    }
}