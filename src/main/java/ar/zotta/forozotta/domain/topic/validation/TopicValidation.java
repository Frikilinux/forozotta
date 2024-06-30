package ar.zotta.forozotta.domain.topic.validation;

import ar.zotta.forozotta.domain.topic.TopicCreateDto;

public interface TopicValidation {
  public void validate(TopicCreateDto createTopicDto);
}
