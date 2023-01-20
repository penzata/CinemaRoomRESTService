package cinema.domain.service;


import cinema.domain.model.CinemaRoom;
import cinema.domain.model.Seat;
import cinema.domain.model.Stats;
import cinema.domain.model.Ticket;
import cinema.domain.model.TokenInfo;

public interface CinemaService {

    CinemaRoom getCinemaRoomInfo();

    Ticket purchaseTicket(Seat seat);

    int ticketPrice(Seat seat);

    Ticket returnTicket(TokenInfo tokenInfo);

    Stats calculateStats(String password);
}