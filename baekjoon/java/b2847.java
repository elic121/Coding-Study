import java.io.*;

public class b2847 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int desc;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(desc);
    }

    private static void solve() {
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] <= arr[i - 1]) {
                desc += arr[i - 1] - arr[i] + 1;
                arr[i - 1] = arr[i] - 1;
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        desc = 0;

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}