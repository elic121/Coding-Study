import java.util.Scanner;
public class b2475 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        for (int i = 0; i < 5; i++){
            int tmp = sc.nextInt();
            sum += tmp * tmp;
        }
        System.out.println(sum % 10);
        sc.close();
    }
}