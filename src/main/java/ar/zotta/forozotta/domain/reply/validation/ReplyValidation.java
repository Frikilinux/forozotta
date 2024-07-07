package ar.zotta.forozotta.domain.reply.validation;

public interface ReplyValidation<T> {
  void validate(T dto);

}
