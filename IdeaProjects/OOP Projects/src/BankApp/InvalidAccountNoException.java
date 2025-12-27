package BankApp;

public class InvalidAccountNoException extends RuntimeException {
    public InvalidAccountNoException(String message) {

        super(message);
    }
}
