import java.util.*;
import java.io.*;

public class b1780 {
    static class Triple {
        int m, z, o;

        public Triple(int m, int z, int o) {
            this.m = m;
            this.z = z;
            this.o = o;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static Triple triple;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.println(triple.m);
        System.out.println(triple.z);
        System.out.println(triple.o);
    }

    private static void solve() {
        int level = n;

        while (level != 0) {
            simulate(level);
            level /= 3;
        }
    }

    private static void simulate(int level) {
        for (int i = 0; i < n / level; i++) {
            for (int j = 0; j < n / level; j++) {

                if (visited[level * i][level * j]) {
                    continue;
                }

                if (!check(level, level * i, level * j)) {
                    continue;
                }

                int pivot = arr[level * i][level * j];

                if (pivot == -1) {
                    triple.m += 1;
                } else if (pivot == 0) {
                    triple.z += 1;
                } else {
                    triple.o += 1;
                }

                for (int k = 0; k < level; k++) {
                    for (int l = 0; l < level; l++) {
                        visited[level * i + k][level * j + l] = true;
                    }
                }
            }
        }
    }

    private static boolean check(int l, int x, int y) {
        int pivot = arr[x][y];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (arr[x + i][y + j] != pivot) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        triple = new Triple(0, 0, 0);
    }
}