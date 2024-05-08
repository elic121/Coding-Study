import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class b9252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        char[] c1 = br.readLine().toCharArray();
        char[] c2 = br.readLine().toCharArray();

        int n1 = c1.length;
        int n2 = c2.length;

        arr = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    if (arr[i][j - 1] >= arr[i - 1][j]) {
                        arr[i][j] = arr[i][j - 1];
                    } else {
                        arr[i][j] = arr[i - 1][j];
                    }
                }
            }
        }

        System.out.println(arr[n1][n2]);

        if (arr[n1][n2] == 0) {
            System.exit(0);
        }

        Stack<Character> stack = new Stack();
        int x = n1;
        int y = n2;

        while (arr[x][y] > 0) {
            if (arr[x][y] == arr[x - 1][y]) {
                x--;
            } else if (arr[x][y] == arr[x][y - 1]) {
                y--;
            } else {
                stack.add(c1[x - 1]);
                x--;
                y--;
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
