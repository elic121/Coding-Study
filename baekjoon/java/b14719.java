import java.io.*;
import java.util.*;

public class b14719 {

  static int H, W;
  static boolean arr[][];
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    arr = new boolean[H][W];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < W; i++) {
      int N = Integer.parseInt(st.nextToken());
      for (int j = 0; j < N; j++) {
        arr[H - j - 1][i] = true;
      }
    }

    solution();
  }

  private static void solution() {
    int cnt = 0;
    for (int i = 0; i < H; i++) {
      cnt += count(i);
    }
    System.out.println(cnt);
  }

  private static int count(int i) {
    int cnt = 0;
    for (int index = 0; index < W; index++) {
      if (!arr[i][index]) {
        continue;
      }

      boolean isWallExist = false;
      int tmpCount = 0;
      for (int j = index + 1; j < W; j++) {
        if (arr[i][j]) {
          isWallExist = true;
          break;
        }
        tmpCount++;
      }

      if (isWallExist) {
        cnt += tmpCount;
      }
    }

    return cnt;
  }
}
