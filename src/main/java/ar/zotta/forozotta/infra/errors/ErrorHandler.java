package ar.zotta.forozotta.infra.errors;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponseDto> validation(ValidationException e) {
    return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> resourceNotFoundvalidation(EntityNotFoundException e) {
    return ResponseEntity.status(404)
        .body(new ErrorResponseDto(HttpStatus.NOT_FOUND, e.getMessage()));
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ErrorResponseDto> unauthorized(TokenExpiredException e) {
    return ResponseEntity.status(401)
        .body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED, e.getMessage() + e.getExpiredOn()));
  }

  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<ErrorResponseDto> signInvalid(JWTVerificationException e) {
    return ResponseEntity.status(401)
        .body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED, e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ValidationResponseDto>> dataValidation(MethodArgumentNotValidException e) {
    List<ValidationResponseDto> errors = e.getFieldErrors().stream().map(ValidationResponseDto::new).toList();
    return ResponseEntity.badRequest().body(errors);
  }
}
