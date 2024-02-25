import java.io.*;

public class b31408 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[100_001];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(str[i])]++;
        }

        int MAX = 0;
        for (int i = 1; i <= 100_000; i++) {
            if (MAX < arr[i]) {
                MAX = arr[i];
            }
        }

        System.out.println(MAX >= ((N % 2 == 1) ? 1 : 0) + (1 + N / 2) ? "NO" : "YES");
    }    
}
