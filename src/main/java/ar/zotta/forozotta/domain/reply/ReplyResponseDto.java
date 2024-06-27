package ar.zotta.forozotta.domain.reply;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.topic.TopicResponseDto;
import ar.zotta.forozotta.domain.user.UserResponseDto;

public record ReplyResponseDto(Long id, String message, UserResponseDto author, TopicResponseDto topic,
    LocalDateTime createdAt, LocalDateTime modifiedAt) {
  public ReplyResponseDto(Reply reply) {
    this(reply.getId(), reply.getMessage(), new UserResponseDto(reply.getAuthor()),
        new TopicResponseDto(reply.getTopic()), reply.getCreatedAt(), reply.getModifiedAt());
  }

}
