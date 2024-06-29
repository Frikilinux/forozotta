package ar.zotta.forozotta.domain.user;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.topic.Topic;

public record UserTopicListResponseDto(Long id, String title, Integer repliesCount, LocalDateTime createdAt,
    LocalDateTime modifiedAt) {
  public UserTopicListResponseDto(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getReplies().size(), topic.getCreatedAt(), topic.getModifiedAt());
  }
}
