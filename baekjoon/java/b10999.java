import java.util.*;
import java.io.*;

public class b10999 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int m;
    static int k;
    static int sqrtN;
    static long[] arr;
    static long[] bucket;
    static long[] bucketSum;
    static long[][] query;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void print() {
        System.out.print(sb.toString());
    }

    private static void append(long value) {
        sb.append(value).append("\n");
    }

    private static void solve() {
        for (int i = 0; i < m + k; i++) {
            simulate(i);
        }
    }

    private static void simulate(int index) {
        long[] q = query[index];

        if (q[0] == 1) {
            update(q[1] - 1, q[2] - 1, q[3]);
        }

        if (q[0] == 2) {
            query(q[1] - 1, q[2] - 1);
        }
    }

    private static void update(long stt, long end, long value) {
        int sttBucketIndex = (int) ((double) stt / sqrtN) + 1;
        int endBucketIndex = (int) (end / sqrtN);

        if (sttBucketIndex > endBucketIndex) {
            for (int i = (int) stt; i <= end; i++) {
                arr[i] += value;
                bucketSum[endBucketIndex] += value;
            }
        } else {
            for (int i = (int) stt; i < sttBucketIndex * sqrtN; i++) {
                arr[i] += value;
                bucketSum[sttBucketIndex - 1] += value;
            }

            for (int i = endBucketIndex * sqrtN; i <= end; i++) {
                arr[i] += value;
                bucketSum[endBucketIndex] += value;
            }

            for (int i = sttBucketIndex; i < endBucketIndex; i++) {
                bucket[i] += value;
                bucketSum[i] += value * sqrtN;
            }
        }
    }

    private static void query(long stt, long end) {
        int sttBucketIndex = (int) ((double) stt / sqrtN) + 1;
        int endBucketIndex = (int) (end / sqrtN);
        long sum = 0;

        if (sttBucketIndex > endBucketIndex) {
            sum = getIntervalSum(
                    (int) stt,
                    (int) end)
                    + bucket[endBucketIndex] * (end - stt + 1);
        } else {
            int lo = sttBucketIndex * sqrtN;
            int hi = endBucketIndex * sqrtN;

            sum += getIntervalSum((int) stt, lo - 1);
            sum += bucket[sttBucketIndex - 1] * (lo - stt);

            sum += getIntervalSum(hi, (int) end);
            sum += bucket[endBucketIndex] * (end - hi + 1);

            for (int i = sttBucketIndex; i < endBucketIndex; i++) {
                sum += bucketSum[i];
            }
        }

        append(sum);
    }

    private static long getIntervalSum(int stt, int end) {
        long sum = 0;
        for (int i = stt; i <= Math.min(end, n - 1); i++) {
            sum += arr[i];
        }
        return sum;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sqrtN = (int) Math.sqrt(n);

        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        int bucketCount = (n + sqrtN - 1) / sqrtN;
        bucket = new long[bucketCount];
        bucketSum = new long[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            bucketSum[i] = getIntervalSum(i * sqrtN, (i + 1) * sqrtN - 1);
        }

        query = new long[m + k][4];
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
            query[i][2] = Integer.parseInt(st.nextToken());

            if (st.hasMoreTokens()) {
                query[i][3] = Long.parseLong(st.nextToken());
            }
        }
    }
}
