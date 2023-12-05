import java.util.*;

public class b2846 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    sc.close();
    int stt = 0;
    int end = 1;
    int max = 0;
    while (end < n) {
      if (arr[end] > arr[end - 1]) {
        int diff = arr[end] - arr[stt];
        if (max < diff) {
          max = diff;
        }
      } else {
        stt = end;
      }
      end += 1;
    }
    System.out.println(max);
  }
}
