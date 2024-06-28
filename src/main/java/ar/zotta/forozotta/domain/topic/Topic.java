package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String message;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  // private Boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private User author;

  @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
  private List<Reply> replies;

  public Topic() {
  }

  public Topic(String title, String message, User author) {
    this.title = title;
    this.message = message;
    this.author = author;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }

  public void updateTopic(UpdateTopicDto updateTopicDto) {
    if (updateTopicDto.title() != null) {
      this.title = updateTopicDto.title();
    }
    if (updateTopicDto.message() != null) {
      this.message = updateTopicDto.message();
    }
    this.modifiedAt = LocalDateTime.now();
  }
  // private String curso;
  // private String response;

}
