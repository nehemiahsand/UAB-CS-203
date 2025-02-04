import java.util.Scanner;
import java.util.Arrays;

public class Problem_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Prompt the user to enter two words and store them in strings
        System.out.print("Enter the first word: ");
        String word1 = input.nextLine();
        System.out.print("Enter the second word: ");
        String word2 = input.nextLine();

        //Create an array that is the length of each word, and contains characters (chars)
        char[] arrayWord1 = new char[word1.length()];
        char[] arrayWord2 = new char[word2.length()];

        //Add the first word letter by letter to the first array
        for (int i = 0; i < word1.length(); i++) {
            arrayWord1[i] = word1.charAt(i);
        }
        //Add the second word letter by letter to the second array
        for (int i = 0; i < word2.length(); i++) {
            arrayWord2[i] = word2.charAt(i);
        }

        //Sort both of the arrays
        Arrays.sort(arrayWord1);
        Arrays.sort(arrayWord2);

        //Check if the two arrays are equal
        if (Arrays.equals(arrayWord1, arrayWord2)) {
            //Tell the user that their words are anagrams
            System.out.println("The word \"" + word1 + "\" and \"" + word2 + "\" are anagrams (they have the same letters)!");
        }
        else {
            //Tell the user that their words are NOT anagrams
            System.out.println("The word \"" + word1 + "\" and \"" + word2 + "\" are NOT anagrams.");
        }

    }
}
