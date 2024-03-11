import java.util.*;
import java.io.*;

public class b13459 {

    static int N, M;
    static char arr[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        perm(0, arr);
        System.out.println(0);
    }

    private static void perm(int depth, char[][] c) {
        if (depth == 10) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            char[][] n = getNewArray(c);
            int result = move(n);

            if (result == 1) {
                System.out.println(1);
                System.exit(0);
            } else if (result == 2) {
                perm(depth + 1, n);
            }

            c = rotate(c);
        }
    }

    private static int move(char[][] orgn) {
        boolean red = false;
        boolean blue = false;
        for (int j = 1; j < orgn[0].length - 1; j++) {
            for (int i = orgn.length - 2; i >= 1; i--) {
                if (orgn[i][j] == 'R' || orgn[i][j] == 'B') {
                    int index = i + 1;
                    while (index < orgn.length - 1 && orgn[index][j] == '.') {
                        index++;
                    }

                    if (orgn[index][j] == 'O') {
                        if (orgn[i][j] == 'R') {
                            red = true;
                        } else if (orgn[i][j] == 'B') {
                            blue = true;
                        }
                        orgn[i][j] = '.';
                        continue;
                    } else {
                        orgn[index - 1][j] = orgn[i][j];
                    }
                    if (index - 1 != i) {
                        orgn[i][j] = '.';
                    }
                }
            }
        }

        int ans = (red && !blue) ? 1 : (!red && !blue) ? 2 : 3;
        return ans;
    }

    private static char[][] getNewArray(char[][] orgn) {
        char[][] temp = new char[orgn.length][orgn[0].length];
        for (int i = 0; i < orgn.length; i++) {
            temp[i] = Arrays.copyOf(orgn[i], orgn[0].length);
        }
        return temp;
    }

    private static char[][] rotate(char[][] orgn) {
        char[][] temp = new char[orgn[0].length][orgn.length];
        for (int i = 0; i < orgn[0].length; i++) {
            for (int j = 0; j < orgn.length; j++) {
                temp[i][j] = orgn[orgn.length - 1 - j][i];
            }
        }
        return temp;
    }

    private static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
