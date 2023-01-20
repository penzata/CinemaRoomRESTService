package cinema.domain.service.Impl;

import cinema.config.CinemaProperties;
import cinema.domain.model.CinemaRoom;
import cinema.domain.model.Seat;
import cinema.domain.model.Stats;
import cinema.domain.model.Ticket;
import cinema.domain.model.TokenInfo;
import cinema.domain.service.CinemaService;
import cinema.exception.AlreadyPurchasedTicketException;
import cinema.exception.ExpiredTokenException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.WrongPasswordException;
import cinema.persistence.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static cinema.exceptionhandler.ExceptionMessage.ALREADY_PURCHASE_TICKET;
import static cinema.exceptionhandler.ExceptionMessage.EXPIRED_TOKEN;
import static cinema.exceptionhandler.ExceptionMessage.SEAT_OUT_OF_BOUNDS;
import static cinema.exceptionhandler.ExceptionMessage.WRONG_PASSWORD;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final SeatRepository seatRepository;
    private final CinemaProperties cinemaProperties;

    public CinemaServiceImpl(SeatRepository seatRepository, CinemaProperties cinemaProperties) {
        this.seatRepository = seatRepository;
        this.cinemaProperties = cinemaProperties;
    }

    @Override
    public CinemaRoom getCinemaRoomInfo() {
        List<Seat> availableSeats = seatRepository.getAvailableSeats();

        return new CinemaRoom(cinemaProperties.getTotalRows(),
                cinemaProperties.getTotalColumns(),
                availableSeats.stream()
                        .map(this::addTicketPrice)
                        .toList());
    }

    private Seat addTicketPrice(Seat seat) {
        int ticketPrice = ticketPrice(seat);
        seat.setTicketPrice(ticketPrice);
        return seat;
    }

    @Override
    public int ticketPrice(Seat seat) {
        return seat.getRowPosition() <= cinemaProperties.getFrontRows() ?
                cinemaProperties.getTicketPriceFront() :
                cinemaProperties.getTicketPriceBack();
    }

    @Override
    public Ticket returnTicket(TokenInfo tokenInfo) {
        return seatRepository.delete(tokenInfo)
                .orElseThrow(() -> new ExpiredTokenException(EXPIRED_TOKEN, tokenInfo));
    }

    @Override
    public Stats calculateStats(String password) {
        String managerPass = "super_secret";
        if (!managerPass.equals(password)) {
            throw new WrongPasswordException(WRONG_PASSWORD, password);
        }
        return seatRepository.calculate()
                .orElseThrow(() -> new WrongPasswordException(WRONG_PASSWORD, password));
    }

    @Override
    public Ticket purchaseTicket(Seat seat) {
        if (!seatRepository.isSeatPresent(seat)) {
            throw new SeatOutOfBoundsException(SEAT_OUT_OF_BOUNDS, seat);
        }
        addTicketPrice(seat);
        return seatRepository.save(seat)
                .orElseThrow(() -> new AlreadyPurchasedTicketException(ALREADY_PURCHASE_TICKET, seat));
    }

}