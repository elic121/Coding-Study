import java.util.*;
import java.io.*;

public class 공통조상 {
    static class Node {
        int me;
        int left;
        int right;

        Node(int me) {
            this.me = me;
        }

        Node(int me, int left) {
            this(me);
            this.left = left;
        }

        Node(int me, int left, int right) {
            this(me, left);
            this.right = right;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t;
    static int v;
    static int e;
    static int s1;
    static int s2;
    static int[] parents;
    static int[] depth;
    static int[] size;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            solve(i);
        }
    }

    private static void solve(int t) {
        search(1, 0);

        int depth1 = depth[s1];
        int depth2 = depth[s2];

        while (depth1 != depth2) {
            if (depth1 > depth2) {
                s1 = parents[s1];
            } else {
                s2 = parents[s2];
            }
            depth1 = depth[s1];
            depth2 = depth[s2];
        }

        while (s1 != s2) {
            s1 = parents[s1];
            s2 = parents[s2];
        }

        System.out.println("#" + t + " " + s1 + " " + size[s1]);
    }

    private static int search(int curr, int dep) {
        int cnt = 1;
        depth[curr] = dep;

        Node node = nodes[curr];
        if (node.left != 0) {
            cnt += search(node.left, dep + 1);
        }
        if (node.right != 0) {
            cnt += search(node.right, dep + 1);
        }

        return size[curr] = cnt;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        s1 = Integer.parseInt(st.nextToken());
        s2 = Integer.parseInt(st.nextToken());

        nodes = new Node[v + 1];
        for (int i = 1; i <= v; i++) {
            nodes[i] = new Node(i);
        }

        parents = new int[v + 1];
        depth = new int[v + 1];
        size = new int[v + 1];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int par = Integer.parseInt(st.nextToken());
            int chi = Integer.parseInt(st.nextToken());

            Node node = nodes[par];
            if (node.left == 0) {
                node.left = chi;
            } else {
                node.right = chi;
            }
            parents[chi] = par;
        }
    }

}
