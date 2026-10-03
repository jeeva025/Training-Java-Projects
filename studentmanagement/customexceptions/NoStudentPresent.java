package studentmanagement.customexceptions;

public class NoStudentPresent extends RuntimeException {
    public NoStudentPresent(String s) {
        super(s);
    }
}
