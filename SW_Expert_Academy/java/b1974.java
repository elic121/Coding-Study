import java.io.*;
import java.util.StringTokenizer;

public class b1974 {

  static int[][] board;

  private static Boolean check_box(int row, int col) {
    Boolean[] arr = new Boolean[9];
    for (int i = 0; i < 9; i++) arr[i] = false;
    for (int i = row; i < row + 3; i++) {
      for (int j = col; j < col + 3; j++) {
        int val = board[i][j];
        if (arr[val - 1] != true) {
          arr[val - 1] = true;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  private static Boolean check_row(int row) {
    Boolean[] arr = new Boolean[9];
    for (int i = 0; i < 9; i++) arr[i] = false;
    for (int i = 0; i < 9; i++) {
      int val = board[row][i];
      if (arr[val - 1] != true) {
        arr[val - 1] = true;
      } else {
        return false;
      }
    }
    return true;
  }

  private static Boolean check_col(int col) {
    Boolean[] arr = new Boolean[9];
    for (int i = 0; i < 9; i++) arr[i] = false;
    for (int i = 0; i < 9; i++) {
      int val = board[i][col];
      if (arr[val - 1] != true) {
        arr[val - 1] = true;
      } else {
        return false;
      }
    }
    return true;
  }

  private static void check(int i) {
    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 3; k++) {
        if (
          !check_box(3 * j, 3 * k) ||
          !check_col(3 * j + k) ||
          !check_row(3 * j + k)
        ) {
          System.out.println("#" + i + " 0");
          return;
        }
      }
    }
    System.out.println("#" + i + " 1");
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 1; i < tc + 1; i++) {
      board = new int[9][9];
      for (int j = 0; j < 9; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 0; k < 9; k++) board[j][k] =
          Integer.parseInt(st.nextToken());
      }
      check(i);
    }
  }
}
