package ar.zotta.forozotta.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopicDTO(
    @NotBlank String title,
    @NotBlank String message,
    @NotNull Long authorId) {

}
