public class Problem_1 {
    public static void main(String[] args) {

        //Create a counter to print
        //Loop through 100 times
        for (int i = 1; i < 101; i++) {
            //Check all the different cases and add one to counter
            //if divisible by 3 and 5
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("FizzBuzz");
            }
            //if divisible by 3
            else if (i % 3 == 0) {
                System.out.println("Fizz");
            }
            //if divisible by 5
            else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
            else {
                System.out.println(i);
            }
        }
    }
}
