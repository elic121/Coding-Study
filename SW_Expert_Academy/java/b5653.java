import java.util.*;
import java.io.*;

public class b5653 {
    static class Cell {
        int age, val;
        Cell(int age, int val) {
            this.age = age;
            this.val = val;
        }

        void dimAge() {
            this.age = this.age - 1;
        }

        @Override
        public String toString() {
            return "("+ age + "," + val +")";
        }
    }
    static int T, N, M, K, CNT;
    static Cell[][] arr;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            solve(tc);
        }
        System.out.println(sb.toString());
    }    

    private static void solve(int tc) {
        for (int i = 0; i <= K; i++) {
            simulate(i);
            // System.out.println("=============" + i + "=============");
            // for (int j = 0; j < 10 + K; j++) {
            //     for (int k = 0; k < 10 + K; k++) {
            //         System.out.print(arr[j][k] == null ? "#" : arr[j][k].val);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            // for (int j = 0; j < 10 + K; j++) {
            //     for (int k = 0; k < 10 + K; k++) {
            //         System.out.print(arr[j][k] == null ? "#" : arr[j][k].age);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            time();
        }
        countCell();
        sb.append('#').append(tc).append(" ").append(CNT).append("\n");
    }

    private static void simulate(int t) {
        Cell[][] temp = new Cell[71][71];

        for (int i = 1; i < 70; i++) {
            for (int j = 1; j < 70; j++) {
                if (arr[i][j] == null) continue;
                temp[i][j] = arr[i][j];

                if (arr[i][j].age == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (arr[nx][ny] != null) continue;
                        if (temp[nx][ny] != null && temp[nx][ny].age == 0) {
                            if (temp[nx][ny].val < arr[i][j].val) {
                                temp[nx][ny] = new Cell(arr[i][j].val, arr[i][j].val);
                            }
                        } else {
                            temp[nx][ny] = new Cell(arr[i][j].val, arr[i][j].val);
                        }
                    }
                }
            }
        }

        arr = temp;
    }

    private static void time() {
        for (int i = 0; i < 71; i++) {
            for (int j = 0; j < 71; j++) {
                if (arr[i][j] == null) continue;
                arr[i][j].dimAge();
            }
        }
    }

    private static void countCell() {
        for (int i = 0; i < 71; i++) {
            for (int j = 0; j < 71; j++) {
                if (arr[i][j] == null) continue;
                CNT += (-arr[i][j].val < arr[i][j].age && arr[i][j].age <= arr[i][j].val) ? 1 : 0;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        CNT = 0;

        arr = new Cell[71][71];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 0) continue;
                arr[i + 10][j + 10] = new Cell(val + 1, val + 1);
            }
        }
    }
}
