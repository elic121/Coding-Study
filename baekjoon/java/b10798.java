import java.util.Scanner;
public class b10798{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[5];
        int MAX = 0; 
        for (int i = 0; i < 5; i++){
            String l = sc.next();
            arr[i] = l;
            if (MAX < l.length()){
                MAX = l.length();
            }
        }
        for (int j = 0; j < MAX; j++){
            for (int i = 0; i < 5; i++){
                String sen = arr[i];
                if (j >= sen.length()){
                    continue;
                }
                char a = sen.charAt(j);
                System.out.print(a);
            }
        }
        sc.close();
    }
}