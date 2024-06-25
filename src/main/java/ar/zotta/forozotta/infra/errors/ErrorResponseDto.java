package ar.zotta.forozotta.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public record ErrorResponseDto(HttpStatus status, String message) {

}
