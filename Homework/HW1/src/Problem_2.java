import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Part a
        System.out.println("bees".compareTo("freedom"));
        System.out.println("crackle".compareTo("stinky"));
        System.out.println("treaty".compareTo("baby"));
        System.out.println("yo-yo".compareTo("cheese"));
        System.out.println("bats".compareTo("bats"));
        System.out.println("grapes".compareTo("grapes"));

        // Part d

        //Create the word to compare users input to
        String wordToCompare = "middle";
        //Prompt the user for input
        System.out.print("Enter a word: ");
        //Store the user's input
        String userWord = input.nextLine();
        //Check if the user's word is after 'middle'
        if (userWord.compareTo(wordToCompare) > 0) {
            System.out.println("The word \"" + userWord + "\" is AFTER \"middle\"");
        }
        //Check if the user's word is before 'middle'
        else if (userWord.compareTo(wordToCompare) < 0) {
            System.out.println("The word \"" + userWord + "\" comes BEFORE \"middle\"");
        }
        else {
            System.out.println("The word \"" + userWord + "\" IS \"middle\"!");
        }
    }
}
