package studentmanagement.customexceptions;

public class InvalidCourseId extends RuntimeException {
    public InvalidCourseId(String s) {
        super(s);
    }
}
