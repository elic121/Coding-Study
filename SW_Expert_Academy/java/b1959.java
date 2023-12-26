package SW_Expert_Academy.java;

import java.io.*;
import java.util.StringTokenizer;

public class b1959 {

  private static int sol(int[] arr1, int[] arr2) {
    if (arr1.length > arr2.length) {
      int[] tmp = arr2;
      arr2 = arr1;
      arr1 = tmp;
    }

    int M = -100_000_000_9;
    for (int i = 0; i < arr2.length - arr1.length + 1; i++) {
      int cnt = 0;
      for (int j = 0; j < arr1.length; j++) {
        cnt += arr1[j] * arr2[i + j];
      }
      if (M < cnt) {
        M = cnt;
      }
    }
    return M;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int i = 1; i < T + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
    
      int[] arr1 = new int[N];
      StringTokenizer st1 = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr1[j] = Integer.parseInt(st1.nextToken());
      }
    
      int[] arr2 = new int[M];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr2[j] = Integer.parseInt(st2.nextToken());
      }

      int ans = sol(arr1, arr2);
      System.out.println("#" + i + " " + ans);
    }
  }
}
