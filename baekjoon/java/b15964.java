import java.util.Scanner;

public class b15964 {
    public static long sol(long x, long y) {
        long a = x + y;
        long b = x - y;
        return a * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextInt();
        long y = sc.nextInt();
        long res = sol(x,y);
        System.out.println(res);
        sc.close();
    }
}
