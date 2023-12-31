import java.io.*;
import java.util.*;

public class b1204 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int tc = 1; tc < N + 1; tc++) {
        br.readLine();
        Map<Integer, Integer> set = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 1000; i++) {
            int val = Integer.parseInt(st.nextToken());
            set.put(val,set.getOrDefault(val, 0) + 1);
        }
        int MAX = -1;
        int MAX_KEY = -1;
        for (int k : set.keySet()) {
            int v = set.get(k);
            if (v > MAX) {
                MAX = v;
                MAX_KEY = k;
            } else if (v == MAX) {
                if (MAX_KEY < k) {
                    MAX_KEY = k;
                }
            }
        }
        System.out.printf("#%d %d\n",tc,MAX_KEY);
    }
  }
}
