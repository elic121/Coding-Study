import java.io.*;
import java.util.*;

public class b12919 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] orin;
    static char[] curr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        ArrayList<Character> ch = new ArrayList<>();
        for (int i = 0; i < curr.length; i++) {
            ch.add(curr[i]);
        }

        dfs(ch);
        System.out.println(0);
    }

    private static void dfs(ArrayList<Character> ch) {
        if (ch.size() == orin.length) {
            if (isEqual(ch)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        boolean isLastA = ch.get(ch.size() - 1) == 'A';
        boolean isFirstB = ch.get(0) == 'B';

        ArrayList<Character> includeB = getArray(ch, false);
        if (!isLastA && isFirstB) {
            dfs(includeB);
            return;
        }

        ArrayList<Character> includeA = getArray(ch, true);
        if (isLastA) {
            dfs(includeA);
        }

        if (isFirstB) {
            dfs(includeB);
        }
    }

    private static ArrayList<Character> getArray(ArrayList<Character> ch, boolean isA) {
        ArrayList<Character> temp = new ArrayList<>();
        if (isA) {
            for (int i = 0; i < ch.size() - 1; i++) {
                temp.add(ch.get(i));
            }
        } else {
            for (int i = ch.size() - 1; i >= 1; i--) {
                temp.add(ch.get(i));
            }
        }
        return temp;
    }

    private static boolean isEqual(ArrayList<Character> ch) {
        for (int i = 0; i < orin.length; i++) {
            if (orin[i] != ch.get(i)) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        orin = br.readLine().toCharArray();
        curr = br.readLine().toCharArray();
    }
}