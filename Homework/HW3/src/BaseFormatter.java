public class BaseFormatter implements NumberFormatter {
    private int baseNumber;

    // Default constructor
    public BaseFormatter() {}

    // Non-default constructor
    public BaseFormatter(int baseN) {
        // Set the base number
        this.baseNumber = baseN;
    }

    @Override
    public String format(int n) {
        // Set the number (n) to a string
        String nString = String.valueOf(n);
        // If the number is negative, call the recursive function without the negative sign
        if(nString.charAt(0) == '-') {
            return recursiveFunction(Integer.parseInt(nString.substring(1)), baseNumber);
        }
        // Else call the recursive function
        else {
            return recursiveFunction(n, baseNumber);
        }
    }

    public String recursiveFunction(int n, int base) {
        // Character key
        String baseChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Base Case
        if(n == 0) {
            return "";
        }
        else {
            // This returns the original number divided by the base
            int whole = n / base;
            // This gives the remainder of what was divided
            int remain = n % base;
            /* This recursively calls the function when it is divided by the base
            and adds the character that corresponds to the remainder
            (for example: a remainder of 12 would return the character "C") */
            return recursiveFunction(whole, base) + baseChars.charAt(remain);
        }
    }
}
