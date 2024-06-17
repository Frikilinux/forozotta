package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserLoginDto;
import ar.zotta.forozotta.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  // @Autowired
  // private TokenService tokenService;

  @PostMapping
  public ResponseEntity<String> userAuth(@RequestBody @Valid UserLoginDto userLoginDto) {

    Authentication authToken = new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password());

    var userAuthenticated = authenticationManager.authenticate(authToken);

    // var jwtToken = tokenService.generateToken((User)
    // userAuthenticated.getPrincipal());

    System.out.println(authToken);
    System.out.println(userAuthenticated);

    return ResponseEntity.ok("OK");

  }

}
