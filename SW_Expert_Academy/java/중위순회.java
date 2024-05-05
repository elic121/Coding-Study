import java.util.*;
import java.io.*;

public class 중위순회 {
    static class Node {
        char c;
        int left;
        int right;

        Node(char c) {
            this.c = c;
        }

        Node(char c, int left) {
            this(c);
            this.left = left;
        }

        Node(char c, int left, int right) {
            this(c, left);
            this.right = right;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static StringTokenizer st;
    static int n;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            init();
            solve(i);
        }
    }

    private static void solve(int t) {
        inOrder(1);
        System.out.println("#" + t + " " + sb.toString());
    }

    private static void inOrder(int curr) {
        Node node = nodes[curr];
        if (node.left > 0) {
            inOrder(node.left);
        }
        sb.append(node.c);
        if (node.right > 0) {
            inOrder(node.right);
        }
    }

    private static void init() throws IOException {
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            if (!st.hasMoreTokens()) {
                nodes[index] = new Node(c);
                continue;
            }

            int leftNum = Integer.parseInt(st.nextToken());

            if (!st.hasMoreTokens()) {
                nodes[index] = new Node(c, leftNum);
                continue;
            }

            int rightNum = Integer.parseInt(st.nextToken());
            nodes[index] = new Node(c, leftNum, rightNum);
        }
    }
}
