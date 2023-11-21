import java.util.Scanner;
public class b2744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        for (char s:str.toCharArray()){
            if ((int)s <= (int)'Z'){
                System.out.print(Character.toLowerCase(s));
            }
            else{
                System.out.print(Character.toUpperCase(s));
            }
        }
        sc.close();
    }
}
