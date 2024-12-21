import java.util.*;
import java.io.*;

public class b12891 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int s;
    static int p;
    static char[] units;
    static int a;
    static int c;
    static int g;
    static int t;
    static int cnt;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    private static void solve() {
        int tempA = 0;
        int tempC = 0;
        int tempG = 0;
        int tempT = 0;

        for (int i = 0; i < p; i++) {
            if (units[i] == 'A') {
                tempA++;
            } else if (units[i] == 'C') {
                tempC++;
            } else if (units[i] == 'G') {
                tempG++;
            } else {
                tempT++;
            }
        }

        cnt += isValid(tempA, tempC, tempG, tempT) ? 1 : 0;

        for (int i = p; i < s; i++) {
            int prev = units[i - p];

            if (prev == 'A') {
                tempA--;
            } else if (prev == 'C') {
                tempC--;
            } else if (prev == 'G') {
                tempG--;
            } else {
                tempT--;
            }

            if (units[i] == 'A') {
                tempA++;
            } else if (units[i] == 'C') {
                tempC++;
            } else if (units[i] == 'G') {
                tempG++;
            } else {
                tempT++;
            }

            // System.out.println(tempA + " " + tempC + " " + tempG + " " + tempT);

            cnt += isValid(tempA, tempC, tempG, tempT) ? 1 : 0;
        }
    }

    private static boolean isValid(int tempA, int tempC, int tempG, int tempT) {
        return tempA >= a && tempC >= c && tempG >= g && tempT >= t;
    }

    private static void print() {
        System.out.println(cnt);
    }

    private static void init() throws IOException {
        cnt = 0;

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        units = new char[s];
        units = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
    }
}