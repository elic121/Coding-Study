import java.util.Scanner;
public class b10869 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int x1 = a + b;
        int x2 = a - b;
        int x3 = a * b;
        int x4 = a / b;
        int x5 = a % b;
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(x5);
        sc.close();
    }
}
