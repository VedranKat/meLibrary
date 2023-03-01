package springStudy.library.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import springStudy.library.dto.ErrorDetail;
import springStudy.library.dto.ErrorMessage;

import java.util.ArrayList;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                new ArrayList<ErrorDetail>());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex, WebRequest request) {
        //ex.
        ErrorMessage message = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                new ArrayList<ErrorDetail>());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ex.getConstraintViolations().stream().toList();
        ErrorMessage message = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                new ArrayList<ErrorDetail>());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                ex.getLocalizedMessage(),
                new ArrayList<ErrorDetail>());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}