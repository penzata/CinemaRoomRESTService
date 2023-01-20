package cinema.exception;

import cinema.domain.model.Seat;

public class SeatOutOfBoundsException extends BusinessException {
    public SeatOutOfBoundsException(String message, Seat seat) {

        super(message);
    }
}