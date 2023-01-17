package cinema.config;

import cinema.domain.model.Seat;
import cinema.domain.model.Ticket;
import cinema.persistence.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final SeatRepository seatRepository;
    private final CinemaProperties cinemaProperties;

    public DataLoader(SeatRepository seatRepository, CinemaProperties cinemaProperties) {
        this.seatRepository = seatRepository;
        this.cinemaProperties = cinemaProperties;
    }

    @Override
    public void run(String... args) {
        Map<Seat, Ticket> seats = new LinkedHashMap<>();
        for (int row = 1; row <= cinemaProperties.getTotalRows(); row++) {
            for (int column = 1; column <= cinemaProperties.getTotalColumns(); column++) {
                seats.put(new Seat(row, column), null);
            }
        }
        log.info("Created Cinema Room with {} rows and {} columns",
                cinemaProperties.getTotalRows(), cinemaProperties.getTotalColumns());
        log.info("Front rows({}) ticket price: {}; Back rows ticket price: {}",
                cinemaProperties.getFrontRows(), cinemaProperties.getTicketPriceFront(),
                cinemaProperties.getTicketPriceBack());
        seatRepository.saveCinemaRoom(seats);
    }

}