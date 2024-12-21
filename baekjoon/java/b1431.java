import java.util.*;
import java.io.*;

public class b1431 {
    static class Element implements Comparable<Element> {
        String str;

        Element(String str) {
            this.str = str;
        }

        private int getSum() {
            int sum = 0;

            for (char c : this.str.toCharArray()) {
                if (Character.isDigit(c)) {
                    sum += Character.getNumericValue(c);
                }
            }

            return sum;
        }

        @Override
        public int compareTo(Element o) {
            if (this.str.length() != o.str.length()) {
                return Integer.compare(this.str.length(), o.str.length());
            }

            if (this.getSum() != o.getSum()) {
                return Integer.compare(this.getSum(), o.getSum());
            }

            for (int i = 0; i < this.str.length(); i++) {
                if (this.str.charAt(i) != o.str.charAt(i)) {
                    return Character.compare(this.str.charAt(i), o.str.charAt(i));
                }
            }

            return 0;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Element[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (Element e : arr) {
            sb.append(e.str);
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new Element[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Element(br.readLine());
        }
    }
}
