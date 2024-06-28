package ar.zotta.forozotta.domain.reply;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
  @Query("SELECT r FROM Reply r WHERE r.topic.id = :topicId")
  List<Reply> getReplyByTopicId(Long topicId);

  @Query("SELECT r FROM Reply r WHERE r.author.id = :userId")
  Optional<List<Reply>> getUserReplies(Long userId);

}
