import java.util.*;
import java.io.*;

public class b14003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;
    static int[] back;
    static ArrayList<Integer> lst;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 1; i < n; i++) {
            if (lst.get(lst.size() - 1) < arr[i]) {
                lst.add(arr[i]);
                back[i] = lst.size() - 1;
            } else {
                binarySearch(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lst.size()).append("\n");

        Stack<Integer> stack = new Stack<>();
        int index = lst.size() - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (back[i] == index) {
                index--;
                stack.add(arr[i]);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void binarySearch(int index) {
        int stt = 0;
        int end = lst.size() - 1;
        int val = arr[index];

        while (stt < end) {
            int mid = (stt + end) / 2;

            // lower bound
            if (lst.get(mid) < val) {
                stt = mid + 1;
            } else {
                end = mid;
            }
        }

        lst.set(end, val);
        back[index] = end;
    }

    private static void init() throws IOException {
        arr = new int[n = Integer.parseInt(br.readLine())];
        back = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lst = new ArrayList<>();
        lst.add(arr[0]);
        back[0] = 0;
    }
}
