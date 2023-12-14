import java.io.*;
import java.util.*;

public class b2740 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    Integer[][] A = new Integer[N][M];
    for (int i = 0; i < N; i++) {
      A[i] =
        Arrays
          .stream(br.readLine().split(" "))
          .map(Integer::parseInt)
          .toArray(Integer[]::new);
    }

    StringTokenizer st1 = new StringTokenizer(br.readLine());
    st1.nextToken();
    int K = Integer.parseInt(st1.nextToken());

    Integer[][] B = new Integer[M][K];
    for (int i = 0; i < M; i++) {
      B[i] =
        Arrays
          .stream(br.readLine().split(" "))
          .map(Integer::parseInt)
          .toArray(Integer[]::new);
    }

    Integer[][] arr = new Integer[N][K];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < K; j++) {
        arr[i][j] = 0;
        for (int m = 0; m < M; m++) {
          arr[i][j] += A[i][m] * B[m][j];
        }
      }
    }
    for (Integer[] a : arr) {
      for (int v : a) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}
