package cinema.rest.dto;

import cinema.domain.model.TokenInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public record TokenDTO(@NotEmpty
                       @JsonProperty("token")
                       String uuid) {
    public TokenInfo toModel() {
        return new TokenInfo(uuid);
    }
}