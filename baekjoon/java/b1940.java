import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b1940 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    int[] arr = Arrays
      .stream(br.readLine().split(" "))
      .mapToInt(Integer::parseInt)
      .toArray();

    Arrays.sort(arr);

    int s = 0;
    int e = N - 1;
    int cnt = 0;
    while (s < e) {
      if (arr[s] + arr[e] < M) {
        s++;
      } else if (arr[s] + arr[e] == M) {
        cnt++;
        s++;
        e--;
      } else {
        e--;
      }
    }
    System.out.println(cnt);
  }
}
