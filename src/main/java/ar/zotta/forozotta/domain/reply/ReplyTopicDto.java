package ar.zotta.forozotta.domain.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyTopicDto(@NotBlank String message, @NotNull Long topicId) {
}
