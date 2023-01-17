package cinema.rest.dto;

import cinema.domain.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TicketDTO(@JsonProperty("token")
                        String token,
                        @JsonProperty("ticket")
                        SeatDTO seatDTO) {

    public static TicketDTO fromModel(Ticket ticket) {
        return new TicketDTO(ticket.getToken(),
                SeatDTO.fromModel(ticket.getSeat()));
    }
}