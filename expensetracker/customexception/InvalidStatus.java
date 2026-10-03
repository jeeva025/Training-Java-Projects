package expensetracker.customexception;

public class InvalidStatus extends Throwable {
    public InvalidStatus(String s) {
        super(s);
    }
}
