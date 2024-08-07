package ar.zotta.forozotta.domain.topic;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query("SELECT t FROM Topic t WHERE t.id = :id")
  Optional<Topic> getTopicById(Long id);

  @Query("SELECT 1 FROM Topic t WHERE t.title = :title AND t.message = :message")
  Optional<Topic> checkDuplicatedTopic(String title, String message);

  @Query("SELECT t FROM Topic t WHERE t.author.id = :userId")
  Optional<Page<Topic>> findUserTopics(Long userId, Pageable pageable);



}
