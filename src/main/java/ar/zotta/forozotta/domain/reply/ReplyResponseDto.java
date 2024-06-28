package ar.zotta.forozotta.domain.reply;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.user.UserResponseDto;

public record ReplyResponseDto(Long id, String message, UserResponseDto author, Long topicId,
    LocalDateTime createdAt, LocalDateTime modifiedAt) {
  public ReplyResponseDto(Reply reply) {
    this(reply.getId(), reply.getMessage(), new UserResponseDto(reply.getAuthor()),
        reply.getTopic().getId(), reply.getCreatedAt(), reply.getModifiedAt());
  }

}
