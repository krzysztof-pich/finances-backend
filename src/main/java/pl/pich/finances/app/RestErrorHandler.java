package pl.pich.finances.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public Object processValidationError(ResponseStatusException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getReason());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }
}
