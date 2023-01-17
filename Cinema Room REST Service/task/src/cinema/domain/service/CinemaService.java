package cinema.domain.service;

import cinema.domain.model.*;

public interface CinemaService {

    CinemaRoom getCinemaRoomInfo();

    Ticket purchaseTicket(Seat seat);

    int ticketPrice(Seat seat);

    Ticket returnTicket(TokenInfo tokenInfo);

    Stats calculateStats(String password);
}