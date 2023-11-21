import java.util.Scanner;

public class b10872 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int sol = 1;
        for (int i = 2; i < x + 1; i++) {
            sol = sol * i;
        }
        if (x == 0)
            System.out.println(1);
        else
            System.out.println(sol);
        sc.close();
    }
}
