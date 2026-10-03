package expensetracker.customexception;

public class AmountShouldBePositive extends RuntimeException {
    public AmountShouldBePositive(String s) {
        super(s);
    }
}
