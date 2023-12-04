import java.util.*;

public class b2576 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] numbers = new int[7];
    for (int i = 0; i < 7; i++) {
      numbers[i] = sc.nextInt();
    }
    sc.close();
    int min = 101;
    int sum = 0;
    for (int x : numbers) {
      if (x % 2 == 1) {
        sum += x;
        if (min > x) {
          min = x;
        }
      }
    }
    if (sum > 0) {
      System.out.println(sum);
      System.out.println(min);
    } else {
      System.out.println(-1);
    }
  }
}
