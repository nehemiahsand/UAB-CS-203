import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class FormatterTester {
    // Calling an arrayList of objects and the base number was approved by Dr. Wagner
    static void prettyPrinter(int[] arr, int baseN, ArrayList<NumberFormatter> obj) {

        for (int num : arr) {
            // Printf formatting taken from ZyBooks section 9.2 (Output Formatting)
            System.out.printf("%-10s%40s\n", "Default Format: ", obj.get(0).format(num));
            System.out.printf("%-10s%30s\n", "Decimal Separated Format: ", obj.get(1).format(num));
            System.out.printf("%-10s%40s\n", "Account Format: ", obj.get(2).format(num));
            System.out.print("Base Format (base " + baseN);
            if(baseN < 10) {
                System.out.printf("%-10s%27s\n", "): ", obj.get(3).format(num));
            }
            else {
                System.out.printf("%-10s%26s\n", "): ", obj.get(3).format(num));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Create a scanner object
        Scanner input = new Scanner(System.in);
        // Create a random object
        Random r1 = new Random();
        // Create an array of 15 elements
        int[] randValues = new int[15];
        // Create an arrayList for the objects
        ArrayList<NumberFormatter> formatObj = new ArrayList<>();

        // Generate 15 random numbers and add them to the array
        for(int i = 0; i < 15; i++) {
            // Random number between -1 million and 1 million
            int randInt = r1.nextInt(-1000000, 1000000);
            // Assign it to index i in the array
            randValues[i] = randInt;
        }

        // Padding
        System.out.println();
        // Get the base from the user
        System.out.println("Enter the base you want to use (2-36): ");
        // Catch the user input
        int userBase = input.nextInt();
        // Padding
        System.out.println();

        // Make sure the user inputs a valid number
        while(true) {
            if(userBase < 2 || userBase > 36) {
                System.out.println("Invalid base.");
                System.out.println("Enter the base you want to use (2-36): ");
                userBase = input.nextInt();
            }
            else {
                break;
            }
        }

        // Create an object of each format
        NumberFormatter f1 = new DefaultFormatter();
        NumberFormatter f2 = new DecimalSeparatorFormatter();
        NumberFormatter f3 = new AccountingFormatter();
        NumberFormatter f4 = new BaseFormatter(userBase);

        // Add the objects to the arrayList
        formatObj.add(f1);
        formatObj.add(f2);
        formatObj.add(f3);
        formatObj.add(f4);

        /* Call the prettyPrinter method and pass the array of numbers,
        the user's base number, and the four objects */
        prettyPrinter(randValues, userBase, formatObj);
    }
}
