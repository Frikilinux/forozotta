package ar.zotta.forozotta.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> validation(ValidationException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> resourceNotFoundvalidation(EntityNotFoundException e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }
}
