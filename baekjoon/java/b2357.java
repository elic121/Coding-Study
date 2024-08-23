import java.util.*;
import java.io.*;

public class b2357 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int m;
    static int sqrtN;
    static int[] arr;
    static int[] bucketMin;
    static int[] bucketMax;
    static int[][] query;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void solve() {
        for (int i = 0; i < m; i++) {
            simulate(i);
        }
    }

    private static void simulate(int index) {
        int[] q = query[index];
        query(q[0] - 1, q[1] - 1);
    }

    private static void query(int stt, int end) {
        int sttBucketIndex = (int) ((double) stt / sqrtN) + 1;
        int endBucketIndex = (int) ((double) end / sqrtN);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (sttBucketIndex > endBucketIndex) {
            for (int i = stt; i <= end; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
        } else {
            int lo = sttBucketIndex * sqrtN;
            int hi = endBucketIndex * sqrtN;

            for (int i = stt; i < lo; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            for (int i = hi; i <= end; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            for (int i = sttBucketIndex; i < endBucketIndex; i++) {
                if (bucketMin[i] < min) {
                    min = bucketMin[i];
                }
                if (bucketMax[i] > max) {
                    max = bucketMax[i];
                }
            }
        }

        sb.append(min).append(" ").append(max).append("\n");
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        query = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
        }

        sqrtN = (int) Math.sqrt(n);
        int bucketCount = (n + sqrtN - 1) / sqrtN;

        bucketMin = new int[bucketCount];
        bucketMax = new int[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int j = 0; j < sqrtN; j++) {
                int index = i * sqrtN + j;
                if (index >= n) {
                    break;
                }

                int value = arr[index];

                if (min > value) {
                    min = value;
                }
                if (max < value) {
                    max = value;
                }
            }

            bucketMin[i] = min;
            bucketMax[i] = max;
        }
    }
}
