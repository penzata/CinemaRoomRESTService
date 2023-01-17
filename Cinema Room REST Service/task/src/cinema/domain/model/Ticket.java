package cinema.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private String token;
    private Seat seat;

    public Ticket() {
    }

    public Ticket(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

}