package cinema.exception;

import cinema.domain.model.TokenInfo;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException(TokenInfo tokenInfo) {
        super("Wrong token!");
    }
}