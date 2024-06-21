package ar.zotta.forozotta.domain.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserRepository;

@Service
public class TopicService {

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  UserRepository userRepository;

  public Topic createTopic(CreateTopicDto topic) {
    Optional<User> user = userRepository.findById(topic.authorId());

    if (user.isEmpty()) {
      throw new RuntimeException("No existe el usuario");
    }

    Topic response = topicRepository.save(new Topic(topic.title(), topic.message(), user.get()));

    return response;
  }

  public List<Topic> getTopics() {
    return topicRepository.findAll();
  }

  public Topic getTopicById(Long id) {
    Optional<Topic> topic = topicRepository.getTopicById(id);

    if (topic.isEmpty()) {
      throw new RuntimeException("Topic no encontrado");
    }
    return topic.get();
  }
  
  // public Topic updateTopic(Long id, UpdateTopicDto updateTopicDto) {
  //   Optional<Topic> topic = topicRepository.getTopicById(id);
    
  //   if (topic.isEmpty()) {
  //     throw new RuntimeException("Topic no encontrado");
  //   }

  //   Topic newTopic = topic.get();

  // }

}
