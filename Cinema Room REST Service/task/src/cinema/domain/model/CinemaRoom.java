package cinema.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaRoom {
    @Min(1)
    private int totalRows;
    @Min(1)
    private int totalColumns;
    private List<Seat> availableSeats;
}