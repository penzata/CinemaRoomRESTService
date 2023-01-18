package cinema.rest.controller;

import cinema.exception.BusinessException;
import cinema.exception.WrongPasswordException;
import cinema.rest.dto.CustomMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<CustomMessageDTO> badRequestHandler(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(new CustomMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<CustomMessageDTO> wrongPasswordHandler(WrongPasswordException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(401)
                .body(new CustomMessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<CustomMessageDTO> errorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(new CustomMessageDTO(ex.getMessage()));
    }
}