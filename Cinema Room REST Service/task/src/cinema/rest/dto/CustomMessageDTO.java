package cinema.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessageDTO(@JsonProperty("error")
                                 String message) {
}