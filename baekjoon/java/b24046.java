import java.io.*;
import java.util.*;

public class b24046 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int swap;
    static int[] arr;
    static ArrayList<Integer> save;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        bubbleSort();
        System.out.println(-1);
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

        if (++swap == k) {
            System.out.println(Arrays.toString(arr));
            System.out.println(arr[x1] + " " + arr[x2]);
            System.exit(0);
        }
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

        for (int i = 0; i < n - 1; i++) {
            save.add(i);
        }
    }
}
