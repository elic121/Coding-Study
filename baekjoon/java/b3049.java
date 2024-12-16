import java.io.*;

public class b3049 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        if (n == 3) {
            System.out.println(0);
            return;
        }

        System.out.println((n) * (n - 1) * (n - 2) * (n - 3) / 24);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
    }
}