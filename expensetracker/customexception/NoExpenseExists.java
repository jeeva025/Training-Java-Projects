package expensetracker.customexception;

public class NoExpenseExists extends RuntimeException {
    public NoExpenseExists(String s) {
        super(s);
    }
}
