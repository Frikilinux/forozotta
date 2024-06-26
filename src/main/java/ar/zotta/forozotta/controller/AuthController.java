package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.user.UserLoginDto;
import ar.zotta.forozotta.domain.user.UserService;
import ar.zotta.forozotta.infra.security.AuthResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<AuthResponseDto> userAuth(@RequestBody @Valid UserLoginDto userLoginDto) {

    AuthResponseDto response = userService.userAuth(userLoginDto);

    return ResponseEntity.ok(response);

  }

}
