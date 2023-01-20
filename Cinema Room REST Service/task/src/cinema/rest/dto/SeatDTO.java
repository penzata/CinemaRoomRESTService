package cinema.rest.dto;

import cinema.domain.model.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import java.util.List;

public record SeatDTO(@Min(1)
                      @JsonProperty("row")
                      int rowPosition,
                      @Min(1)
                      @JsonProperty("column")
                      int columnPosition,
                      @JsonProperty("price")
                      int ticketPrice) {


    public static SeatDTO fromModel(Seat seat) {
        return new SeatDTO(seat.getRowPosition(),
                seat.getColumnPosition(),
                seat.getTicketPrice());
    }

    public static List<SeatDTO> fromModel(List<Seat> seats) {
        return seats.stream()
                .map(s -> new SeatDTO(s.getRowPosition(),
                        s.getColumnPosition(),
                        s.getTicketPrice()))
                .toList();
    }

    public Seat toModel() {
        return new Seat(rowPosition, columnPosition);
    }
}