package cinema.rest.controller;

import cinema.domain.model.CinemaRoom;
import cinema.domain.model.Stats;
import cinema.domain.model.Ticket;
import cinema.domain.service.CinemaService;
import cinema.rest.dto.CinemaRoomDTO;
import cinema.rest.dto.ReturnedTicketDTO;
import cinema.rest.dto.SeatDTO;
import cinema.rest.dto.StatsDTO;
import cinema.rest.dto.TicketDTO;
import cinema.rest.dto.TokenDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public CinemaRoomDTO getCinemaRoom() {
        CinemaRoom cinemaRoom = cinemaService.getCinemaRoomInfo();
        return CinemaRoomDTO.fromModel(cinemaRoom);
    }

    @PostMapping("/purchase")
    public TicketDTO purchaseTicket(@RequestBody SeatDTO seatDTO) {
        Ticket ticket = cinemaService.purchaseTicket(seatDTO.toModel());
        return TicketDTO.fromModel(ticket);
    }

    @PostMapping("/return")
    public ReturnedTicketDTO refundTicket(@RequestBody TokenDTO tokenDTO) {
        Ticket returnedTicket = cinemaService.returnTicket(tokenDTO.toModel());
        return ReturnedTicketDTO.fromModel(returnedTicket);
    }

    @PostMapping("/stats")
    public StatsDTO getStats(@RequestParam(required = false, name = "password") String password) {
        Stats stats = cinemaService.calculateStats(password);
        return StatsDTO.fromModel(stats);
    }
}