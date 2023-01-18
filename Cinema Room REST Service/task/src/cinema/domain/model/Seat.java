package cinema.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seat {
    private int rowPosition;
    private int columnPosition;
    private int ticketPrice;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAvailable;

    public Seat(int rowPosition, int columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.isAvailable = true;
    }

}