package cinema.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {
    int currentIncome;
    int numberOfAvailableSeats;
    int numberOfPurchasedTickets;

    public Stats(int currentIncome, int numberOfAvailableSeats, int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public Stats() {
    }
}
