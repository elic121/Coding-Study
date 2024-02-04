package Comparator;
import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {
    
    static class Human {
        int age, height;
        Human(int age, int height) {
            this.age = age;
            this.height = height;
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
        
        Arrays.sort(arr, new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                if (h1.age != h2.age) {
                    return Integer.compare(h1.age, h2.age);
                }
                if (h1.height != h2.height) {
                    return Integer.compare(h1.height, h2.height);
                } 
                return 0;
            }
        });

        for (Human h : arr) {
            System.out.println(h);
        }
    }
}
