package cinema.rest.controller;

import cinema.exception.BusinessException;
import cinema.exception.WrongPasswordException;
import cinema.rest.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorDTO> badRequestHandler(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<ErrorDTO> wrongPasswordHandler(WrongPasswordException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(401)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorDTO> errorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(new ErrorDTO(ex.getMessage()));
    }
}