package ar.zotta.forozotta.domain.reply;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
  @Query("SELECT r FROM Reply r WHERE r.topic.id = :topicId")
  List<Reply> getReplyByTopicId(Long topicId);

  @Query("SELECT r FROM Reply r WHERE r.author.id = :userId")
  Optional<Page<Reply>> getUserReplies(Long userId, Pageable Pageable);

  @Query("SELECT 1 FROM Reply r WHERE r.topic.id = :topicId AND r.message = :message")
  Optional<Reply> checkDuplicatedReply(Long topicId, String message);

}
