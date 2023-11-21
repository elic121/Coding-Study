import java.util.Scanner;
public class b10807 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            int x = sc.nextInt();
            arr[i] = x; 
        }
        int v = sc.nextInt();
        int cnt = 0;
        for (int val : arr){
            if (val == v){
                cnt = cnt + 1;
            }
        }
        System.out.println(cnt);
        sc.close();
    }
}
