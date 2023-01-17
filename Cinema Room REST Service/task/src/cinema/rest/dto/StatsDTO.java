package cinema.rest.dto;

import cinema.domain.model.Stats;

public record StatsDTO(int currentIncome,
                       int numberOfAvailableSeats,
                       int numberOfPurchasedTickets) {
    public static StatsDTO fromModel(Stats stats) {
        return new StatsDTO(stats.getCurrentIncome(),
                stats.getNumberOfAvailableSeats(),
                stats.getNumberOfPurchasedTickets());
    }
}