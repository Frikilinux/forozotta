package ar.zotta.forozotta.domain.topic.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.zotta.forozotta.domain.topic.TopicCreateDto;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;

@Component
public class DuplicatedTopic implements TopicValidation<TopicCreateDto> {
  @Autowired
  private TopicRepository topicRepository;

  @Override
  public void validate(TopicCreateDto createTopicDto) {
    Optional<Topic> topic = topicRepository.checkDuplicatedTopic(createTopicDto.title(), createTopicDto.message());

    if (topic.isPresent()) {
      throw new ValidationException("El topic ya existe.");
    }

  }

}
