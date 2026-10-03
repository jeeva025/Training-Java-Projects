package expensetracker.customexception;

public class DescriptionCannotBeEmpty extends RuntimeException {
    public DescriptionCannotBeEmpty(String s) {
        super(s);
    }
}
