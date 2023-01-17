package cinema.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CinemaRoom {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    public CinemaRoom(int totalRows, int totalColumns, List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public CinemaRoom() {
    }
}