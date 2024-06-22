import java.util.*;
import java.io.*;

public class b20327 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int r;
    static int size;
    static int[][] arr;
    static int[][] order;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        simulate();
        print();
    }

    private static void simulate() {
        for (int i = 0; i < r; i++) {
            int o = order[i][0];
            int s = 1 << order[i][1];
            if (o <= 4) {
                calculate(0, 0, size, s, o);
            } else if (o == 5) {
                flipAllUD(s);
            } else if (o == 6) {
                flipAllRL(s);
            } else if (o == 7) {
                rotateAll(s, 1);
            } else if (o == 8) {
                rotateAll(s, 3);
            }
        }
    }

    private static void calculate(int x, int y, int s, int goal, int action) {
        if (s == goal) {
            doAction(x, y, s, action);
            return;
        }

        int next = s >> 1;
        calculate(x, y, next, goal, action);
        calculate(x, y + next, next, goal, action);
        calculate(x + next, y, next, goal, action);
        calculate(x + next, y + next, next, goal, action);
    }

    private static void doAction(int x, int y, int s, int action) {
        if (action == 1) {
            flipUD(x, y, s);
        } else if (action == 2) {
            flipRL(x, y, s);
        } else if (action == 3) {
            rotateAndChange(x, y, s, 1);
        } else if (action == 4) {
            rotateAndChange(x, y, s, 3);
        } else if (action == 5) {
            flipAllUD(s);
        } else if (action == 6) {
            flipAllRL(s);
        }
    }

    private static void rotateAndChange(int x, int y, int s, int count) {
        int[][] temp = new int[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                temp[i][j] = arr[x + i][y + j];
            }
        }

        for (int i = 0; i < count; i++) {
            temp = rotate(temp);
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                arr[x + i][y + j] = temp[i][j];
            }
        }
    }

    private static int[][] rotate(int[][] rot) {
        int size = rot.length;
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = rot[size - j - 1][i];
            }
        }

        return temp;
    }

    private static void flipUD(int x, int y, int s) {
        int[][] temp = new int[s][s];

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                temp[i][j] = arr[x + s - i - 1][y + j];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                arr[x + i][y + j] = temp[i][j];
            }
        }
    }

    private static void flipRL(int x, int y, int s) {
        int[][] temp = new int[s][s];

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                temp[i][j] = arr[x + i][y + s - j - 1];
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                arr[x + i][y + j] = temp[i][j];
            }
        }
    }

    private static void rotateAll(int s, int count) {
        rotateAndChange(0, 0, size, count);
        calculate(0, 0, size, s, count == 1 ? 4 : 3);
    }

    private static void flipAllUD(int s) {
        flipUD(0, 0, size);
        calculate(0, 0, size, s, 1);
    }

    private static void flipAllRL(int s) {
        flipRL(0, 0, size);
        calculate(0, 0, size, s, 2);
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        size = 1 << n;

        arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[r][2];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
