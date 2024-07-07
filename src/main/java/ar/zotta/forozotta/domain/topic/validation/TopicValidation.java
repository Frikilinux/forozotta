package ar.zotta.forozotta.domain.topic.validation;

public interface TopicValidation<T> {
  void validate(T dto);
}
