import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2669 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int arr[][] = new int[100][100];
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        arr[i][j] = 0;
      }
    }
    for (int i = 0; i < 4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int sx = Integer.parseInt(st.nextToken());
      int sy = Integer.parseInt(st.nextToken());
      int ex = Integer.parseInt(st.nextToken());
      int ey = Integer.parseInt(st.nextToken());
      for (int k = sx; k < ex; k++) {
        for (int j = sy; j < ey; j++) {
          arr[k][j] = 1;
        }
      }
    }

    int cnt = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (arr[i][j] == 1) cnt++;
      }
    }

    System.out.println(cnt);
  }
}
