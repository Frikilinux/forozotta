package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.user.UserLoginDto;
import ar.zotta.forozotta.domain.user.UserService;
import ar.zotta.forozotta.infra.security.AuthResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autorización")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Crea un token de autorización.")
  @PostMapping
  public ResponseEntity<AuthResponseDto> userAuth(@RequestBody @Valid UserLoginDto userLoginDto) {

    AuthResponseDto response = userService.userAuth(userLoginDto);

    return ResponseEntity.ok(response);

  }

}
