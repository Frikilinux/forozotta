package ar.zotta.forozotta.domain.topic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query("SELECT t FROM Topic t WHERE t.id = :id")
  Optional<Topic> getTopicById(Long id);

  @Query("SELECT t FROM Topic t WHERE t.title = :title AND t.message = :message")
  Optional<Topic> checkDuplicatedTopic(String title, String message);

}
