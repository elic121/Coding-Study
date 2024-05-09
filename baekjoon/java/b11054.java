import java.util.*;
import java.io.*;

public class b11054 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] asc;
    static int[] desc;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            int[] a = getMaxLength(asc, 0, i);
            int[] b = getMaxLength(desc, 0, n - i);

            int rest = 0;
            if (a[1] != -1 && b[1] != -1) {
                if (a[1] == b[1]) {
                    rest = -1;
                }
            }

            max = Math.max(max, a[0] + b[0] + rest);
        }
        System.out.println(max);
    }

    private static int[] getMaxLength(int[] arr, int stt, int end) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = stt; i < end; i++) {
            int find = Collections.binarySearch(list, arr[i]);

            find = find >= 0 ? find : (-find - 1);
            if (list.size() <= find) {
                list.add(arr[i]);
            } else {
                list.set(find, arr[i]);
            }
        }
        return new int[] { list.size(), !list.isEmpty() ? list.get(list.size() - 1) : -1 };
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        asc = new int[n];
        desc = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            asc[i] = Integer.parseInt(st.nextToken());
            desc[n - i - 1] = asc[i];
        }
    }
}
