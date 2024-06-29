package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import ar.zotta.forozotta.domain.user.UserResponseDto;

public record TopicListResponseDto(Long id, String title, Integer repliesCount, UserResponseDto author,
    LocalDateTime createdAt, LocalDateTime modifiedAt) {
  public TopicListResponseDto(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getReplies().size(), new UserResponseDto(topic.getAuthor()),
        topic.getCreatedAt(), topic.getModifiedAt());
  }
}
