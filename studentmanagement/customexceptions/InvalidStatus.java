package studentmanagement.customexceptions;

public class InvalidStatus extends RuntimeException{
     public InvalidStatus(String s){
        super(s);
    }
}
