import java.io.*;
import java.math.BigInteger;

public class b2407 {

    static BigInteger[][] arr;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        m = n - m < m ? n - m : m;

        arr = new BigInteger[n+1][n+1];
        dp();

        System.out.println(arr[n][m]);
    
    }

    private static void dp() {
        for (int i = 0; i < n+1; i++) {
            arr[i][i] = BigInteger.ONE;
            arr[i][0] = BigInteger.ONE;
        }
        
        for (int i = 2; i < n+1; i++) {
            for (int j = 1; j < i; j++) {
                arr[i][j] = arr[i-1][j].add(arr[i-1][j-1]);
            }
        }
    }
}