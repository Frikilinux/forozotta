package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import ar.zotta.forozotta.domain.reply.ReplyResponseDto;
import ar.zotta.forozotta.domain.user.UserResponseDto;

public record TopicResponseDto(Long id, String title, String message, UserResponseDto author,
    List<ReplyResponseDto> replies, LocalDateTime createdAt,
    LocalDateTime modifiedAt) {
  public TopicResponseDto(Topic topic, List<ReplyResponseDto> replies) {
    this(topic.getId(), topic.getTitle(), topic.getMessage(), new UserResponseDto(topic.getAuthor()), replies, topic.getCreatedAt(), topic.getModifiedAt());
  }
}
