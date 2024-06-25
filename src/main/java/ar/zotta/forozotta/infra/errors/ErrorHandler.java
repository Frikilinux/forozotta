package ar.zotta.forozotta.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> validation(ValidationException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> resourceNotFoundvalidation(EntityNotFoundException e) {
    return ResponseEntity.status(404)
        .body(new ErrorResponseDto(HttpStatus.NOT_FOUND, e.getMessage()));
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ErrorResponseDto> unauthorized(TokenExpiredException e) {
    return ResponseEntity.status(401)
        .body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED, e.getMessage()));
  }

  @ExceptionHandler(SignatureVerificationException.class)
  public ResponseEntity<ErrorResponseDto> signInvalid(SignatureVerificationException e) {
    return ResponseEntity.status(401)
        .body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED, e.getMessage()));
  }
}
