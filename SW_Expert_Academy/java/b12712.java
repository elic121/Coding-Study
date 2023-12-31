import java.io.*;
import java.util.*;

public class b12712 {

  static int[][] arr;
  static int N;
  static int M;
  static int[][] d1 = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
  static int[][] d2 = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

  private static int sol(int i, int j) {
    int cnt1 = arr[i][j];
    int cnt2 = arr[i][j];
    for (int[] d : d1) {
      cnt1 += sol_sub(i, j, d);
    }
    for (int[] d : d2) {
      cnt2 += sol_sub(i, j, d);
    }
    return cnt1 > cnt2 ? cnt1 : cnt2;
  }

  private static int sol_sub(int i, int j, int[] d) {
    int x = i + d[0];
    int y = j + d[1];
    int tmp = 0;
    int cnt = 1;
    while (x >= 0 && x < N && y >= 0 && y < N) {
      tmp += arr[x][y];
      x += d[0];
      y += d[1];
      cnt++;
      if (cnt >= M) {
        return tmp;
      }
    }
    return tmp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 0; i < tc; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      arr = new int[N][N];

      for (int j = 0; j < N; j++) {
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int k = 0; k < N; k++) {
          arr[j][k] = Integer.parseInt(st1.nextToken());
        }
      }
      int MAX = -1;
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          int s = sol(j, k);
          MAX = MAX < s ? s : MAX;
        }
      }
      System.out.printf("#%d %d\n", i + 1, MAX);
    }
  }
}
