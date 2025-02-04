package Problem_6;

public class Contact {
    private String contactName;
    private String contactEmail;
    private String contactPhoneNumber;

    //Constructor
    public Contact(String name, String email, String phoneNumber) {
        contactName = name;
        contactEmail = email;
        contactPhoneNumber = phoneNumber;
    }

    public void setName(String name) {
        contactName = name;
    }

    public void setEmail(String email) {
        contactEmail = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        contactPhoneNumber = phoneNumber;
    }

    public String getName() {
        return contactName;
    }

    public String getEmail() {
        return contactEmail;
    }

    public String getPhoneNumber() {
        return contactPhoneNumber;
    }

    public String toString() {
        return "Name: " + contactName + "\nEmail: " + contactEmail + "\nPhone Number: " + contactPhoneNumber;
    }
}
