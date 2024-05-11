import java.io.*;
import java.util.Arrays;

public class b2448 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][-1 + (n << 1)];

        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], ' ');
        }

        divideAndConquer(0, n - 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < -1 + (n << 1); j++) {
                sb.append(arr[i][j]);
            }
            if (i == n - 1) {
                System.out.println(sb.toString());
                break;
            }
            sb.append('\n');
        }
    }

    private static void divideAndConquer(int x, int y, int n) {
        if (n == 3) {
            arr[x][y] = '*';
            arr[x + 1][y + 1] = arr[x + 1][y - 1] = '*';
            for (int j = -2; j <= 2; j++) {
                arr[x + 2][y + j] = '*';
            }
            return;
        }

        int div = n >> 1;
        divideAndConquer(x, y, div);
        divideAndConquer(x + div, y + div, div);
        divideAndConquer(x + div, y - div, div);

    }
}
