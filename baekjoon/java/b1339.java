import java.io.*;
import java.util.*;

public class b1339 {
    static class Alpha {
        int weight;
        char ch;

        Alpha(int weight, char ch) {
            this.weight = weight;
            this.ch = ch;
        }

        void addWeight(int index) {
            this.weight += Math.pow(10, index);
        }
    }

    static int N, sum, size;
    static String[] str;
    static Alpha[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = new String[N];
        alpha = new Alpha[10];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            str[i] = s;
            char[] arr = s.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                boolean find = false;
                for (int k = 0; k <= size; k++) {
                    if (alpha[k] == null)
                        continue;
                    if (alpha[k].ch == arr[j]) {
                        alpha[k].addWeight(arr.length - j);
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    alpha[size++] = new Alpha((int) Math.pow(10, arr.length - j), arr[j]);
                }
            }
        }

        Arrays.sort(alpha, 0, size, (x, y) -> {
            if (x.weight != y.weight) {
                return y.weight - x.weight;
            }
            return 0;
        });

        for (int i = 0; i < str.length; i++) {
            sum += calc(str[i]);
        }

        System.out.println(sum);

    }

    private static int calc(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < size; j++) {
                if (alpha[j].ch == str.charAt(i)) {
                    cnt += (9 - j) * Math.pow(10, str.length() - i - 1);
                }
            }
        }
        return cnt;
    }
}
