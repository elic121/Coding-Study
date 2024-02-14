import java.util.*;
import java.io.*;

public class b15903 {
    static long N, M;
    static PriorityQueue<Long> que;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        
        que = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            que.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            long x = que.poll();
            long y = que.poll();
            long sum = x + y;

            que.add(sum);
            que.add(sum);
        }

        long sum = 0;
        for (long v : que) {
            sum += v;
        }

        System.out.println(sum);
    }    
}
