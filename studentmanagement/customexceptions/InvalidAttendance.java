package studentmanagement.customexceptions;

public class InvalidAttendance extends RuntimeException {
    public InvalidAttendance(String s){
        super(s);
    }
}
