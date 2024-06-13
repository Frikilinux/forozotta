package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;

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
  // private Boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private User author;

  public Topic(String title, String message, User author) {
    this.title = title;
    this.message = message;
    this.author = author;
    this.createdAt = LocalDateTime.now();
  }

  // private String curso;
  // private String response;

}
