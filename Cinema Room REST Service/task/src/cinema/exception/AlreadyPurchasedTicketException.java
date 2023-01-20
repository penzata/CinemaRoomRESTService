package cinema.exception;

import cinema.domain.model.Seat;

public class AlreadyPurchasedTicketException extends BusinessException {

    public AlreadyPurchasedTicketException(String message, Seat seat) {
        super(message);
    }
}