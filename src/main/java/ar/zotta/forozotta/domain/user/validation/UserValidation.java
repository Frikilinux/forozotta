package ar.zotta.forozotta.domain.user.validation;

import ar.zotta.forozotta.domain.user.UserRegisterDto;

public interface UserValidation<T> {
  public void validate(T object);
}
