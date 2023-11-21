import java.util.Scanner;
public class b1008 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        var a = sc.next();
        var b = sc.next();
        double c = Double.parseDouble(a) / Double.parseDouble(b);
        System.out.println(c);
        sc.close();
    }
}
