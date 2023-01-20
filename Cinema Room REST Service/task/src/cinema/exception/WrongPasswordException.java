package cinema.exception;

public class WrongPasswordException extends BusinessException {

    public WrongPasswordException(String message, String password) {
        super(message);
    }
}