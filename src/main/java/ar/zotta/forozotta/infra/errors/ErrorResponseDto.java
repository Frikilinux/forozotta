package ar.zotta.forozotta.infra.errors;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(HttpStatus status, String message) {

}
