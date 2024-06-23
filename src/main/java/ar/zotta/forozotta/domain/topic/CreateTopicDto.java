package ar.zotta.forozotta.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicDto(
        @NotBlank String title,
        @NotBlank String message) {

}
