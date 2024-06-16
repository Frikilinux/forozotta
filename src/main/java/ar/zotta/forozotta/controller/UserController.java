package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.user.RegisterUserDto;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDto;
import ar.zotta.forozotta.domain.user.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid RegisterUserDto registerUserDto) {

    User user = userService.registerUser(registerUserDto);

    UserResponseDto userResponse = userService.userResponse(user);

    return ResponseEntity.status(201).body(userResponse);
  }

}
