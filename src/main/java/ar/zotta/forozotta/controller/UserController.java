package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.user.RegisterUserDTO;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO) {

    User user = userRepository.save(new User(registerUserDTO));
    UserResponseDTO userResponse = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());

    return ResponseEntity.status(201).body(userResponse);
  }

}
