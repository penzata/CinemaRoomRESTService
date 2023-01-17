package cinema.rest.dto;

import cinema.domain.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ReturnedTicketDTO(@JsonProperty("returned_ticket")
                                SeatDTO seatDTO) {
    public static ReturnedTicketDTO fromModel(Ticket returnedTicket) {
        return new ReturnedTicketDTO(SeatDTO.fromModel(returnedTicket.getSeat()));
    }
}