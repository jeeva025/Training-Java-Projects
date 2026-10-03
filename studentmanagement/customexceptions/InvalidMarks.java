package studentmanagement.customexceptions;

public class InvalidMarks extends RuntimeException {
    public InvalidMarks(String s) {
        super(s);
    }
}
