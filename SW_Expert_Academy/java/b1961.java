import java.io.*;
import java.util.*;

public class b1961 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int tc = 1; tc < N + 1; tc++) {
      int size = Integer.parseInt(br.readLine());
      int[][] arr = new int[size][size];
      for (int i = 0; i < size; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < size; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      String[][] ans = new String[size][3];
      for (int j = 0; j < size; j++) {
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
          sb.append(arr[i][j]);
        }
        String val = sb.toString();
        ans[j][0] = val;
      }

      for (int i = size - 1; i >= 0; i--) {
        StringBuilder sb = new StringBuilder();
        for (int j = size - 1; j >= 0; j--) {
          sb.append(arr[i][j]);
        }
        String val = sb.toString();
        ans[size - 1 - i][1] = val;
      }

      for (int j = size - 1; j >= 0; j--) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
          sb.append(arr[i][j]);
        }
        String val = sb.toString();
        ans[size - 1 - j][2] = val;
      }

      System.out.println("#" + tc);
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < 3; j++) {
          System.out.print(ans[i][j] + " ");
        }
        System.out.print("\n");
      }
    }
  }
}
