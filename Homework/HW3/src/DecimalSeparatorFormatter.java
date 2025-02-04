public class DecimalSeparatorFormatter implements NumberFormatter{

    // Default constructor
    public DecimalSeparatorFormatter() {}

    @Override
    public String format(int n) {
        // Set the number (n) to a string
        String nString = String.valueOf(n);
        // If the number is negative, call the recursive function without the negative sign
        if(nString.charAt(0) == '-') {
            return "-" + recursiveFunction(Integer.parseInt(nString.substring(1)));
        }
        // Else call the recursive function
        else {
            return recursiveFunction(n);
        }
    }

    public String recursiveFunction(int n) {
        // Convert the number (n) to a string
        String nString = String.valueOf(n);
        // Base case
        if (nString.length() <= 3) {
            // If base case is reached return the remaining numbers
            return nString;
        }
        else {
            // Recursively call a substring and add the last three numbers with a comma to the stack
            return recursiveFunction(Integer.parseInt(nString.substring(0, nString.length() - 3))) + ","
                    + nString.substring(nString.length() - 3);
        }
    }
}
