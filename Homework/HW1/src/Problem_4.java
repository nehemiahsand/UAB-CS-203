import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int omittedNumber = 0;
        //Make an array from 1-100
        int[] array100 = new int[101];
        //Add the corresponding numbers into the array
        for (int j = 0; j < array100.length; j++) {
            array100[j] = j;
        }
        //Ask the user what number to remove from the array
        System.out.print("Enter the number you want removed: ");
        int numRemoved = input.nextInt();
        array100[numRemoved] = 0;
        //Iterate through with a counter to see if they are equivalent
        for (int i = 1; i < array100.length; i++) {
            if (array100[i] == i) {
                System.out.println(array100[i]);
            }
            else if (array100[i] != i) {
                omittedNumber = i;
            }
        }
        System.out.println("The number that was omitted from the list is " + omittedNumber);
    }
}
