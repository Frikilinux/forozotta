package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ar.zotta.forozotta.domain.reply.ReplyResponseDto;
import ar.zotta.forozotta.domain.user.UserResponseDto;

public record TopicResponseDto(Long id, String title, String message, UserResponseDto author,
    List<ReplyResponseDto> replies, LocalDateTime createdAt,
    LocalDateTime modifiedAt, UUID uuid) {
  public TopicResponseDto(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getMessage(), new UserResponseDto(topic.getAuthor()),
        topic.getReplies().stream().map(ReplyResponseDto::new).toList(), topic.getCreatedAt(), topic.getModifiedAt(),
        topic.getUuid());
  }
}
