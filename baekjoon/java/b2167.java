import java.io.*;
import java.util.*;

public class b2167 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    Integer[][] arr = new Integer[row][col];

    for (int i = 0; i < row; i++) {
      StringTokenizer val = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        arr[i][j] = Integer.parseInt(val.nextToken());
      }
    }
    int N = Integer.parseInt(br.readLine());
    for (int q = 0; q < N; q++) {
      StringTokenizer index = new StringTokenizer(br.readLine());
      int i = Integer.parseInt(index.nextToken()) - 1;
      int j = Integer.parseInt(index.nextToken()) - 1;
      int x = Integer.parseInt(index.nextToken()) - 1;
      int y = Integer.parseInt(index.nextToken()) - 1;

      int cnt = 0;
      for (int X = i; X < x + 1; X++) {
        for (int Y = j; Y < y + 1; Y++) {
            cnt += arr[X][Y];
        }
      }
      System.out.println(cnt);
    }
    br.close();
}
}
