package BankApp;

public class IncorrectAccountPinException extends RuntimeException {
    public IncorrectAccountPinException(String message) {

        super(message);
    }
}
