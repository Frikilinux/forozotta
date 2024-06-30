package ar.zotta.forozotta.domain.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.domain.reply.ReplyRepository;
import ar.zotta.forozotta.domain.topic.validation.TopicValidation;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserRepository;
import ar.zotta.forozotta.infra.security.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@Service
public class TopicService {

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  ReplyRepository replyRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthService authService;

  @Autowired
  List<TopicValidation> topicValidations;

  public Topic createTopic(TopicCreateDto topic) {

    User user = authService.getAutorizedUser();

    topicValidations.forEach(tv -> tv.validate(topic));

    Topic response = topicRepository.save(new Topic(topic.title(), topic.message(), user));

    return response;
  }

  public Page<Topic> getTopics(Pageable pageable) {
    Page<Topic> topics = topicRepository.findAll(pageable);

    return topics;
    // return topicRepository.findAll();

  }

  public Topic getTopicById(Long id) {
    Optional<Topic> topic = topicRepository.getTopicById(id);

    if (topic.isEmpty()) {
      throw new EntityNotFoundException("Topic no encontrado");
    }

    return topic.get();
  }

  public Topic updateTopic(Long id, UpdateTopicDto updateTopicDto) {

    Topic topic = getTopicById(id);

    if (authService.checkOwner(topic.getAuthor().getId())) {
      throw new ValidationException("El propietario del topic no es igual al usuario logueado.");
    }

    topic.updateTopic(updateTopicDto);

    return topic;

  }

  public void deleteById(Long id) {
    Topic topic = getTopicById(id);

    if (authService.checkOwner(topic.getAuthor().getId())) {
      throw new ValidationException("El propietario del topic no es igual al usuario logueado.");
    }

    topicRepository.deleteById(id);
  }

}
