package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import ar.zotta.forozotta.domain.user.UserResponseDto;

public record TopicCreateResponseDto(Long id, String title, String message, UserResponseDto author,
    LocalDateTime createdAt, LocalDateTime modifiedAt) {
  public TopicCreateResponseDto(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getMessage(), new UserResponseDto(topic.getAuthor()),
        topic.getCreatedAt(), topic.getModifiedAt());
  }
}
