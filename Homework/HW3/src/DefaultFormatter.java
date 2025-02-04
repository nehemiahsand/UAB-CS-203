public class DefaultFormatter implements NumberFormatter {

    // Default constructor
    public DefaultFormatter() {}

    @Override
    public String format(int n) {
        // Make the number (n) into a string
        String nString = String.valueOf(n);
        // Return the string
        return nString;
    }
}
