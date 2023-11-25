import java.lang.Math;
import java.util.Scanner;

public class b10093 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long x = sc.nextLong();
    long y = sc.nextLong();
    sc.close();
    if (x > y) {
      long tmp = y;
      y = x;
      x = tmp;
    }
    System.out.println(Math.max(y - x - 1, 0));
    for (long i = x + 1; i < y; i++) {
      System.out.print(i + " ");
    }
  }
}
