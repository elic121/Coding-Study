import java.util.*;
import java.io.*;

public class b15898 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[][] pos = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
    static int n;
    static int max;
    static int[][] gamaNum;
    static int[][] gamaCol;
    static int[][][] num;
    static int[][][] col;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        perm(0, 0);
        System.out.println(max);
    }

    private static void perm(int depth, int visited) {
        if (depth == 3) {
            max = Math.max(max, getScore());
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            for (int c = 0; c < 4; c++) {
                int[][] saveNum = getCopy(gamaNum, 5);
                int[][] saveCol = getCopy(gamaCol, 5);

                int[][] tempNum = rotate(num[i], c);
                int[][] tempCol = rotate(col[i], c);
                for (int t = 0; t < 4; t++) {
                    put(tempNum, tempCol, t);
                    perm(depth + 1, visited | 1 << i);

                    gamaNum = getCopy(saveNum, 5);
                    gamaCol = getCopy(saveCol, 5);
                }
            }
        }
    }

    private static void put(int[][] nu, int[][] co, int t) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = i + pos[t][0];
                int y = j + pos[t][1];

                gamaNum[x][y] += nu[i][j];
                gamaNum[x][y] = gamaNum[x][y] < 0 ? 0 : gamaNum[x][y];
                gamaNum[x][y] = gamaNum[x][y] > 9 ? 9 : gamaNum[x][y];
                gamaCol[x][y] = co[i][j] != 'W' ? co[i][j] : gamaCol[x][y];
            }
        }
    }

    private static int[][] rotate(int[][] arr, int count) {
        int[][] temp = getCopy(arr, 4);

        for (int i = 0; i < count; i++) {
            int[][] t = new int[4][4];

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    t[j][k] = temp[4 - k - 1][j];
                }
            }

            temp = getCopy(t, 4);
        }

        return temp;
    }

    private static int convertFromEng(int c) {
        return c == 'R' ? 7 : c == 'B' ? 5 : c == 'G' ? 3 : c == 'Y' ? 2 : 0;
    }

    private static int[][] getCopy(int[][] arr, int size) {
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            temp[i] = Arrays.copyOf(arr[i], size);
        }
        return temp;
    }

    private static int getScore() {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                score += gamaNum[i][j] * convertFromEng(gamaCol[i][j]);
            }
        }
        return score;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        max = 0;

        gamaNum = new int[5][5];
        gamaCol = new int[5][5];
        num = new int[n][4][4];
        col = new int[n][4][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    num[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for (int j = 0; j < 4; j++) {
                char[] ch = br.readLine().toCharArray();
                for (int k = 0; k < 4; k++) {
                    col[i][j][k] = ch[k << 1];
                }
            }
        }
    }
}
