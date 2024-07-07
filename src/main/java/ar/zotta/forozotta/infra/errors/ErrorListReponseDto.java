package ar.zotta.forozotta.infra.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorListReponseDto(HttpStatus status, List<ValidationResponseDto> errors) {

}
