package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.user.UserRegisterDto;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDto;
import ar.zotta.forozotta.domain.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRegisterDto registerUserDto,
      UriComponentsBuilder uriComponentsBuilder) {

    User user = userService.registerUser(registerUserDto);

    URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).body(new UserResponseDto(user));
  }
}
