package ar.zotta.forozotta.domain.user.validation;

public interface UserValidation<T> {
  public void validate(T object);
}
