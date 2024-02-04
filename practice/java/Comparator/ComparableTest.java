package Comparator;
import java.util.Arrays;

public class ComparableTest {
    
    static class Human implements Comparable<Human> {
        int age, height;
        Human(int age, int height) {
            this.age = age;
            this.height = height;
        }

        @Override
        public int compareTo(Human h) {
            if (this.age != h.age) {
                return Integer.compare(this.age, h.age);
            }
            if (this.height != h.height) {
                return Integer.compare(this.height, h.height);
            }

            return 0;
        }

        @Override
        public String toString() {
            return "age : " + age + " height : " + height;
        }
    }
    
    static Human[] arr;
    public static void main(String[] args) {
        arr = new Human[3];
        arr[0] = new Human(10, 21);
        arr[1] = new Human(12, 19);
        arr[2] = new Human(10, 20);
        
        Arrays.sort(arr);
        for (Human h : arr) {
            System.out.println(h);
        }
    }
}
