package Problem_6;
import java.util.Scanner;

public class ContactTester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Get the user's name
        System.out.print("Enter the contact's name: ");
        String contactName = input.nextLine();
        //Get the user's email
        System.out.print("Enter the contact's email: ");
        String contactEmail = input.nextLine();
        //Get the user's phone number
        System.out.print("Enter the contact's phone number: ");
        String contactNumber = input.nextLine();
        System.out.println();

        //Create a new contact using the user's input
        Contact person1 = new Contact(contactName, contactEmail, contactNumber);
        //Print out the contact
        System.out.println(person1);
        //Prompt the user to edit their phone number
        System.out.println("\nEnter a new phone number: ");
        String newContactNumber = input.nextLine();
        //Update the contact
        person1.setPhoneNumber(newContactNumber);
        System.out.println();
        //Print out the updated contact
        System.out.println(person1);
    }
}
