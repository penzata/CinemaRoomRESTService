package cinema.domain.model;

import lombok.Getter;

@Getter
public class TokenInfo {
    private final String uniqueIdentifier;

    public TokenInfo(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

}