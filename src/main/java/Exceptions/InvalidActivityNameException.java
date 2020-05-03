package Exceptions;

public class InvalidActivityNameException extends RuntimeException{
    public InvalidActivityNameException(String message){
        super(message);
    }
}
