package ar.zotta.forozotta.domain.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyUpdateDto(@NotBlank String message) {
  public ReplyUpdateDto(Reply reply) {

    this(reply.getMessage());
  }
}
