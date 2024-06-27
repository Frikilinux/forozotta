package ar.zotta.forozotta.domain.reply;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean solution;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private User author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topic_id")
  private Topic topic;

  public Reply() {
  }

  public Reply(String message, User author, Topic topic) {
    this.message = message;
    this.author = author;
    this.topic = topic;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
    this.solution = false;
  }

  public void updateReply(UpdateReplyDto updateReplyDto) {
    if (updateReplyDto.message() != null) {
      this.message = updateReplyDto.message();
    }
    this.modifiedAt = LocalDateTime.now();
  }

}
