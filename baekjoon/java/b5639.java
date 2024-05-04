import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b5639 {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Node head;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        head = null;
        for (; ;) {
            try {
                int t = Integer.parseInt(br.readLine());
                find(t);
            } catch(Exception e) {
                break;
            }
        }

        printPostOrder(head);
    }

    private static void find(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node curr = head;
        Node parent = null;
        while (curr != null) {
            parent = curr;
            if (curr.data > data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (parent.data > data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    private static void printPostOrder(Node curr) {
        if (curr == null) {
            return;
        }
        printPostOrder(curr.left);
        printPostOrder(curr.right);
        System.out.println(curr.data);
    }
}
