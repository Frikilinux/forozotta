package ar.zotta.forozotta.domain.user.validation;

import ar.zotta.forozotta.domain.user.UserRegisterDto;

public interface UserValidation {
  public void validate(UserRegisterDto userRegisterDto);
}
