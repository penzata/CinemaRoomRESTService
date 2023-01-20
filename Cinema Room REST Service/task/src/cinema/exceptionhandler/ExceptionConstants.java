package cinema.exceptionhandler;

public class ExceptionConstants {
    public static final String EXPIRED_TOKEN = "Wrong token!";
    public static final String WRONG_PASSWORD = "The password is wrong!";
    public static final String SEAT_OUT_OF_BOUNDS = "The number of a row or a column is out of bounds!";
    public static final String ALREADY_PURCHASE_TICKET = "The ticket has been already purchased!";

    private ExceptionConstants() {
    }
}
