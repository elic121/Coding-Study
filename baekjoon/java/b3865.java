import java.util.*;
import java.io.*;

public class b3865 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static String first;
    static HashSet<String> set;
    static HashMap<String, Integer> map;
    static HashMap<String, ArrayList<String>> members;

    public static void main(String[] args) throws IOException {
        for (;;) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            init();
            solve();
        }
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        dq.add(first);

        int count = 0;
        while (!dq.isEmpty()) {
            String academy = dq.pop();

            for (String name : members.get(academy)) {
                if (set.contains(name)) {
                    continue;
                }

                if (!map.containsKey(name)) {
                    count++;
                } else {
                    dq.add(name);
                }
                set.add(name);
            }
        }

        System.out.println(count);
    }

    private static void init() throws IOException {
        set = new HashSet<>();
        map = new HashMap<>();
        members = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] sentence = br.readLine().split(":");
            String academy = sentence[0];

            if (i == 1) {
                first = academy;
            }

            String[] names = sentence[1].split(",");

            map.put(academy, i);
            for (String str : names) {
                if (str.endsWith(".")) {
                    str = str.substring(0, str.length() - 1);
                }

                if (members.containsKey(academy)) {
                    ArrayList<String> arr = members.get(academy);
                    arr.add(str);
                    members.put(academy, arr);
                } else {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(str);
                    members.put(academy, arr);
                }
            }
        }
    }
}