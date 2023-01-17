package cinema.exception;

import cinema.domain.model.Seat;

public class AlreadyPurchasedTicketException extends BusinessException {

    public AlreadyPurchasedTicketException(Seat seat) {
        super("The ticket has been already purchased!");
    }
}