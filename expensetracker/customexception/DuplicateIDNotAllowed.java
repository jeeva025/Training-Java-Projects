package expensetracker.customexception;

public class DuplicateIDNotAllowed extends RuntimeException {
    public DuplicateIDNotAllowed(String s) {
        super(s);
    }
}
