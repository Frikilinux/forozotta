package ar.zotta.forozotta.domain.user;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.reply.Reply;

public record UserRepliesListResponseDto(Long id, String message, LocalDateTime createdAt, LocalDateTime modifiedAt,
    Long topicId, Boolean solution) {
  public UserRepliesListResponseDto(Reply reply) {
    this(reply.getId(), reply.getMessage(), reply.getCreatedAt(), reply.getModifiedAt(), reply.getTopic().getId(),
        reply.getSolution());
  }
}
