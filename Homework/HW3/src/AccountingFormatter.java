public class AccountingFormatter implements NumberFormatter {

    // Default constructor
    public AccountingFormatter() {}

    @Override
    public String format(int n) {
        String nString = String.valueOf(n);
        if(n < 0)
            return "(" + nString.substring(1) + ")";
        else
            return nString;
    }
}