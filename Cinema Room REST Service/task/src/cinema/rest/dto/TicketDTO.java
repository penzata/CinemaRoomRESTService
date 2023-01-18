package cinema.rest.dto;

import cinema.domain.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"token", "seatDTO"})
public record TicketDTO(String token,
                        @JsonProperty("ticket")
                        SeatDTO seatDTO) {

    public static TicketDTO fromModel(Ticket ticket) {
        return new TicketDTO(ticket.getToken(),
                SeatDTO.fromModel(ticket.getSeat()));
    }
}