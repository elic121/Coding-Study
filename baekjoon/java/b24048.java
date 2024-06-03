import java.io.*;
import java.util.*;

public class b24048 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int swap;
    static int[] arr;
    static int[] comp;
    static ArrayList<Integer> save;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bubbleSort();
        System.out.println(0);
    }

    private static void bubbleSort() {
        ArrayList<Integer> temp = new ArrayList<>();

        while (!save.isEmpty()) {
            for (int v : save) {
                if (arr[v] > arr[v + 1]) {
                    swap(v, v + 1);

                    if (v - 1 >= 0) {
                        temp.add(v - 1);
                    }
                }
            }

            save = new ArrayList<>(temp);
            temp.clear();
        }

    }

    private static void swap(int x1, int x2) {
        int temp = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = temp;

        ++swap;

        if (swap > k) {
            System.out.println(0);
            System.exit(0);
        }

        if (arr[x1] != comp[x1]) {
            return;
        }

        if (arr[x2] != comp[x2]) {
            return;
        }

        if (isEqual(arr, comp)) {
            System.out.println(1);
            System.exit(0);
        }

    }

    private static boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        swap = 0;

        save = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        comp = new int[n];
        boolean diff = false;
        for (int i = 0; i < n; i++) {
            comp[i] = Integer.parseInt(st.nextToken());
            if (arr[i] != comp[i]) {
                diff = true;
            }
        }

        if (!diff) {
            System.out.println(1);
            System.exit(0);
        }

        for (int i = 0; i < n - 1; i++) {
            save.add(i);
        }
    }
}
