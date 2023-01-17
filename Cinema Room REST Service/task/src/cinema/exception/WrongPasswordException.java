package cinema.exception;

public class WrongPasswordException extends BusinessException {

    public WrongPasswordException(String password) {
        super("The password is wrong!");
    }
}