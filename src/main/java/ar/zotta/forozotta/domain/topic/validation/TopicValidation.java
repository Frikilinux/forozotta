package ar.zotta.forozotta.domain.topic.validation;

import ar.zotta.forozotta.domain.topic.CreateTopicDto;

public interface TopicValidation {
  public void validate(CreateTopicDto createTopicDto);
}
