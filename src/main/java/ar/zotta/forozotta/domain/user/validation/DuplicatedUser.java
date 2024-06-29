package ar.zotta.forozotta.domain.user.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserRegisterDto;
import ar.zotta.forozotta.domain.user.UserRepository;
import jakarta.validation.ValidationException;

@Component
public class DuplicatedUser implements UserValidation<UserRegisterDto> {
  @Autowired
  UserRepository userRepository;

  @Override
  public void validate(UserRegisterDto userRegisterDto) {
    Optional<User> user = userRepository.findUserByEmail(userRegisterDto.email());
    if (user.isPresent()) {
      throw new ValidationException("El email ya está registrado");
    }
  }

}
