import java.util.*;
import java.io.*;

public class b15658 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static int add;
    static int sub;
    static int mul;
    static int div;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(max);
        System.out.println(min);
    }

    private static void solve() {
        perm(0, add, sub, mul, div, "");
    }

    private static void perm(int depth, int add, int sub, int mul, int div, String command) {
        if (depth == n - 1) {
            int result = calc(command);

            if (result == Integer.MIN_VALUE) {
                return;
            }

            if (min > result) {
                min = result;
            }

            if (max < result) {
                max = result;
            }

            return;
        }

        if (add > 0) {
            perm(depth + 1, add - 1, sub, mul, div, command + "0");
        }

        if (sub > 0) {
            perm(depth + 1, add, sub - 1, mul, div, command + "1");
        }

        if (mul > 0) {
            perm(depth + 1, add, sub, mul - 1, div, command + "2");
        }

        if (div > 0) {
            perm(depth + 1, add, sub, mul, div - 1, command + "3");
        }
    }

    private static int calc(String command) {
        int num = checkSignAndCalcNum(arr[0], arr[1], command.charAt(0));

        if (num == Integer.MIN_VALUE) {
            return num;
        }

        for (int i = 2; i <= command.length(); i++) {
            num = checkSignAndCalcNum(num, arr[i], command.charAt(i - 1));

            if (num == Integer.MIN_VALUE) {
                return num;
            }
        }

        return num;
    }

    private static int checkSignAndCalcNum(int x, int y, char sign) {
        if (sign == '0') {
            return x + y;
        } else if (sign == '1') {
            return x - y;
        } else if (sign == '2') {
            return x * y;
        } else {
            if (y == 0) {
                return Integer.MIN_VALUE;
            }

            if (x >= 0 && y > 0) {
                return x / y;
            } else if (x < 0 && y > 0) {
                return -(Math.abs(x) / y);
            } else if (x >= 0 && y < 0) {
                return -(x / Math.abs(y));
            } else {
                return Math.abs(x) / Math.abs(y);
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        add = Integer.parseInt(st.nextToken());
        sub = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());
    }
}