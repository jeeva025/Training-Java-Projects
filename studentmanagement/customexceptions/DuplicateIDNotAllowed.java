package studentmanagement.customexceptions;

public class DuplicateIDNotAllowed extends RuntimeException {
    public DuplicateIDNotAllowed(String s) {
        super(s);
    }
}
