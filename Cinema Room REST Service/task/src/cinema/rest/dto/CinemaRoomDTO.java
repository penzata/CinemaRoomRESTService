package cinema.rest.dto;

import cinema.domain.model.CinemaRoom;
import cinema.domain.model.Seat;

import java.util.List;

public record CinemaRoomDTO(int totalRows,
                            int totalColumns,
                            List<SeatDTO> availableSeats) {

    public static CinemaRoomDTO fromModel(CinemaRoom cinemaRoom) {
        return new CinemaRoomDTO(cinemaRoom.getTotalRows(),
                cinemaRoom.getTotalColumns(),
                SeatDTO.fromModel(cinemaRoom.getAvailableSeats()));
    }

}