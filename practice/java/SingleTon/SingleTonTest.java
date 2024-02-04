package SingleTon;

public class SingleTonTest {
    
    static class Human {
        private static Human instnace = new Human(10, 20);

        int age, height;
        Human() {};

        Human(int age, int height) {
            this.age = age;
            this.height = height;
        }

        public static Human getInstatnce() {
            return instnace;
        }

        @Override
        public String toString() {
            return "age : " + age + " height : " + height;
        }
    }
    
    static Human[] arr;
    public static void main(String[] args) {
        arr = new Human[3];
        arr[0] = Human.getInstatnce();
        arr[0].age = 12;
        arr[1] = Human.getInstatnce();
        arr[2] = Human.getInstatnce();

        for (Human h : arr) {
            System.out.println(h);
        }
    }
}
