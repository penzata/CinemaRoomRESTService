package cinema.rest.dto;

import cinema.domain.model.TokenInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDTO(@JsonProperty("token")
                       String uuid) {
    public TokenInfo toModel() {
        return new TokenInfo(uuid);
    }
}