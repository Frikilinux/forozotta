package ar.zotta.forozotta.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query("SELECT t FROM Topic t WHERE t.id = :id")
  Topic getTopicById(Long id);
  
}
