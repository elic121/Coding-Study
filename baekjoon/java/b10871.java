import java.util.Scanner;

public class b10871 {
    public static void sol1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int val = sc.nextInt();
            arr[i] = val;
        }
        for (int q : arr) {
            if (q < X) {
                System.out.print(q + " ");
            }
        }
        sc.close();
    }

    public static void sol2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int val = sc.nextInt();
            if (val < X) {
                System.out.println(val + " ");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        // sol1();
        sol2();
    }
}
