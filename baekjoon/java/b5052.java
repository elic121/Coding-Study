import java.util.*;
import java.io.*;

public class b5052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int n;
    static boolean isValid;
    static String[] arr;
    static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            init();
            solve();
            print();
        }
    }

    private static void print() {
        System.out.println(isValid ? "YES" : "NO");
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            String s = arr[i];
            if (set.contains(s)) {
                isValid = false;
                break;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(0));
            for (int j = 0; j < s.length() - 1; j++) {
                set.add(sb.toString());
                sb.append(s.charAt(j + 1));
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        isValid = true;

        set = new HashSet<>();
        arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (x, y) -> {
            return y.length() - x.length();
        });
    }
}