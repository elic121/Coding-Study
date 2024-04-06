import java.util.*;
import java.io.*;

public class b1865 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int INF = 987654321;
    static int T, N, M, W;
    static int[] stt, end, weight;
    static int[] dist;
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            init();
            solve();
        }
    }

    private static void solve() {
        System.out.println(bellmanFord(0) ? "YES" : "NO");
    }

    private static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < W + 2 * M; j++) {
                int s = stt[j];
                int e = end[j];
                int w = weight[j];
                if (dist[e] > dist[s] + w) {
                    dist[e] = dist[s] + w;
                }
            }
        }

        for (int j = 0; j < W + 2 * M; j++) {
            int s = stt[j];
            int e = end[j];
            int w = weight[j];
            if (dist[e] > dist[s] + w) {
                return true;
            }
        }
        return false;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        stt = new int[2 * M + W];
        end = new int[2 * M + W];
        weight = new int[2 * M + W];
        dist = new int[N];

        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            stt[2 * i] = s - 1;
            end[2 * i] = e - 1;
            weight[2 * i] = t;
            stt[2 * i + 1] = e - 1;
            end[2 * i + 1] = s - 1;
            weight[2 * i + 1] = t;
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            stt[i + 2 * M] = s - 1;
            end[i + 2 * M] = e - 1;
            weight[i + 2 * M] = -t;
        }
    }
}
