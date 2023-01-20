package cinema.persistence.repository;

import cinema.config.CinemaProperties;
import cinema.domain.model.Seat;
import cinema.domain.model.Stats;
import cinema.domain.model.Ticket;
import cinema.domain.model.TokenInfo;
import cinema.exception.ExpiredTokenException;
import cinema.exception.SeatOutOfBoundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static cinema.exceptionhandler.ExceptionMessage.EXPIRED_TOKEN;
import static cinema.exceptionhandler.ExceptionMessage.SEAT_OUT_OF_BOUNDS;


@Slf4j
@Repository
public class SeatRepository {
    private final CinemaProperties cinemaProperties;
    private Map<Seat, Ticket> seats;

    public SeatRepository(CinemaProperties cinemaProperties) {
        this.cinemaProperties = cinemaProperties;
    }

    public void saveCinemaRoom(Map<Seat, Ticket> seats) {
        this.seats = seats;
    }

    public Optional<Ticket> save(Seat seat) {
        if (isSeatAvailable(seat)) {
            Seat seatInRoom = seats.keySet().stream()
                    .filter(s -> s.getRowPosition() == seat.getRowPosition() &&
                            s.getColumnPosition() == seat.getColumnPosition())
                    .findFirst()
                    .orElseThrow(() -> new SeatOutOfBoundsException(SEAT_OUT_OF_BOUNDS, seat));
            Ticket ticket = new Ticket(String.valueOf(UUID.randomUUID()), seat);
            seats.put(seatInRoom, ticket);
            changeSeatToNotAvailable(seat);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    private boolean isSeatAvailable(Seat seat) {
        return seats.keySet().stream()
                .filter(s -> s.getRowPosition() == seat.getRowPosition() &&
                        s.getColumnPosition() == seat.getColumnPosition())
                .anyMatch(Seat::isAvailable);
    }

    private void changeSeatToNotAvailable(Seat seat) {
        seats.keySet().stream()
                .filter(s -> s.getRowPosition() == seat.getRowPosition() &&
                        s.getColumnPosition() == seat.getColumnPosition())
                .findFirst()
                .ifPresent(s -> s.setAvailable(false));
        seat.setAvailable(false);
    }

    public Optional<Stats> calculate() {
        return Optional.of(new Stats(totalIncome(),
                getAvailableSeats().size(),
                getPurchasedTickets()));
    }

    private int totalIncome() {
        return seats.values().stream()
                .filter(Objects::nonNull)
                .mapToInt(t -> t.getSeat().getTicketPrice())
                .sum();
    }

    public List<Seat> getAvailableSeats() {
        return seats.keySet().stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    private int getPurchasedTickets() {
        return (int) seats.values().stream()
                .filter(Objects::nonNull)
                .count();
    }

    public Optional<Ticket> delete(TokenInfo tokenInfo) {
        Optional<Ticket> ticket = seats.values().stream()
                .filter(Objects::nonNull)
                .filter(t -> tokenInfo.getUniqueIdentifier().equals(t.getToken()))
                .findFirst();
        Seat seatFromTicket = ticket
                .orElseThrow(() -> new ExpiredTokenException(EXPIRED_TOKEN, tokenInfo))
                .getSeat();

        Seat seatInRoom = seats.keySet().stream()
                .filter(s -> s.getRowPosition() == seatFromTicket.getRowPosition() &&
                        s.getColumnPosition() == seatFromTicket.getColumnPosition())
                .findFirst()
                .orElseThrow(() -> new SeatOutOfBoundsException(SEAT_OUT_OF_BOUNDS, seatFromTicket));

        seatInRoom.setAvailable(true);
        seats.replace(seatInRoom, null);
        ticket.orElseThrow(() -> new ExpiredTokenException(EXPIRED_TOKEN, tokenInfo)).setToken(null);
        return ticket;
    }

    public boolean isSeatPresent(Seat seat) {
        return seat.getRowPosition() > 0 &&
                seat.getRowPosition() <= cinemaProperties.getTotalRows() &&
                seat.getColumnPosition() > 0 &&
                seat.getColumnPosition() <= cinemaProperties.getTotalColumns();
    }

}