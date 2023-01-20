package cinema.exception;

import cinema.domain.model.TokenInfo;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException(String message, TokenInfo tokenInfo) {

        super(message);
    }
}