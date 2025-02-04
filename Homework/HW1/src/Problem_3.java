import java.util.Scanner;

public class Problem_3 {
    public static int[] Factors(int num) {
        //factors are 2, 3, 5, and 7
        //initialize an array
        int[] differentFactors = new int[15];
        //count how many times you go through the loop
        int counter = 0;
        while (num != 1) {
            if (num % 7 == 0) {
                differentFactors[counter] = 7;
                num = num / 7;
            } else if (num % 5 == 0) {
                differentFactors[counter] = 5;
                num = num / 5;
            } else if (num % 3 == 0) {
                differentFactors[counter] = 3;
                num = num / 3;
            } else if (num % 2 == 0) {
                differentFactors[counter] = 2;
                num = num / 2;
            }
            else {
                differentFactors[counter] = num;
                //Only add the number one if the user's number is not divisible by anything
                if (counter == 0)
                    differentFactors[counter + 1] = 1;
                break;
            }
            counter++;
        }
        return differentFactors;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number to be evaluated: ");
        int userNumber = input.nextInt();
        int[] myArray = Factors(userNumber);
        System.out.println("The factors of " + userNumber + " are: ");
        for (int element : myArray) {
            if (element != 0) {
                System.out.println(element);
            }
            else {
                break;
            }
        }
    }
}
