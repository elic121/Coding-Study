/**
 * 	메모리  :   11504   KB
 *  시간    :	76      ms
 */

import java.io.*;

public class b2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int c = N / 5;
        while (c >= 0 && (N - 5 * c) % 3 != 0) c--;
        
        System.out.println(c >= 0 ? (c + (N - 5 * c) / 3) : -1);
    }    
}
