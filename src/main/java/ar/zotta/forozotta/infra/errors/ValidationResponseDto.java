package ar.zotta.forozotta.infra.errors;

import org.springframework.validation.FieldError;

public record ValidationResponseDto(String field, String message) {
  public ValidationResponseDto(FieldError fieldError) {
    this(fieldError.getField(), fieldError.getDefaultMessage());
  }
}
