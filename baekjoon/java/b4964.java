import java.util.*;
import java.io.*;

public class b4964 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int cnt;
    static String[] words;
    static ArrayList<Character> arr;
    static HashSet<Character> inZero;
    static HashMap<Character, Integer> map;

    public static void main(String[] args) throws IOException {
        for (;;) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            init();
            solve();
            print();
        }
    }

    private static void print() {
        System.out.println(cnt);
    }

    private static void solve() {
        backtrack(0, 0);
    }

    private static void backtrack(int depth, int visited) {
        if (depth == arr.size()) {
            cnt += isValid() ? 1 : 0;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if ((visited & 1 << i) != 0) {
                continue;
            }

            char ch = arr.get(depth);
            if (i == 0 && inZero.contains(ch)) {
                continue;
            }

            map.put(ch, i);
            backtrack(depth + 1, visited | 1 << i);
        }
    }

    private static boolean isValid() {
        int sum = 0;

        for (int i = 0; i < n - 1; i++) {
            sum += convert(words[i]);
        }

        return sum == convert(words[n - 1]);
    }

    private static int convert(String str) {
        int pivot = 1;
        int sum = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            sum += pivot * map.get(str.charAt(i));
            pivot *= 10;
        }
        return sum;
    }

    private static void init() throws IOException {
        cnt = 0;
        words = new String[n];
        map = new HashMap<>();
        inZero = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            words[i] = word;
            if (word.length() != 1) {
                inZero.add(word.charAt(0));
            }
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);

                if (!map.containsKey(ch)) {
                    map.put(ch, 0);
                }
            }
        }

        arr = new ArrayList<>(map.keySet());
    }
}