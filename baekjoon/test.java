package baekjoon;

import java.io.*;
import java.util.*;

public interface test {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            int max = N * 2;
            int count = 0;
            while (N <= max) {

                int check = 0;
                if (N < 2) {
                    N++;
                    continue;
                }
                for (int i = 2; i <= Math.sqrt(N); i++) {
                    if (N % i == 0) {
                        check++;
                        break;
                    }
                }
                if (check == 0) {
                    count++;
                }
                N++;
            }
            sb.append(count).append("\n");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
    }

}
