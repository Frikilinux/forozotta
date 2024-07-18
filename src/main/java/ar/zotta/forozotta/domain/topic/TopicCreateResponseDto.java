package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.zotta.forozotta.domain.user.UserResponseDto;

public record TopicCreateResponseDto(Long id, String title, String message, UserResponseDto author,
    LocalDateTime createdAt, LocalDateTime modifiedAt, String uuid) {
  public TopicCreateResponseDto(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getMessage(), new UserResponseDto(topic.getAuthor()),
        topic.getCreatedAt().truncatedTo(ChronoUnit.SECONDS), topic.getModifiedAt().truncatedTo(ChronoUnit.SECONDS),
        topic.getUuid());
  }
}
