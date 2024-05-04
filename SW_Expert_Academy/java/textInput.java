import java.util.Random;

public class textInput {
    static final 섬지키기 solution = new 섬지키기();
    static Random random = new Random();
    static int n = 6;
    static int[][] arr = new int[6][6];

    
    public static void main(String[] args) {
        arr = new int[][] {
            {1, 1, 1, 1, 2, 1},
            {2, 1, 3, 3, 1, 2},
            {1, 1, 1, 2, 3, 1},
            {3, 1, 2, 3, 4, 1},
            {2, 1, 1, 1, 2, 1},
            {3, 2, 1, 3, 3, 3}
        };

        solution.init(n, arr);
    }
}
