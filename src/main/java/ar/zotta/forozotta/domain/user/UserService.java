package ar.zotta.forozotta.domain.user;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.infra.security.AuthResponseDto;
import ar.zotta.forozotta.infra.security.TokenService;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  public User registerUser(RegisterUserDto registerUserDto) {

    if (finUser(registerUserDto.email()).isPresent()) {
      throw new RuntimeException("El email ya existe");
    }

    User user = new User(registerUserDto);

    // Reemplaza el password recibido con uno encryptado
    user.setPassword(passwordEncoder.encode(registerUserDto.password()));

    return userRepository.save(user);

  }

  public AuthResponseDto userAuth(UserLoginDto userLoginDto) {
    Authentication authRequestToken = new UsernamePasswordAuthenticationToken(userLoginDto.email(),
        userLoginDto.password());

    Authentication userAuthenticated = authenticationManager.authenticate(authRequestToken);

    String jwtToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
    User user = (User) userAuthenticated.getPrincipal();

    Date expiresAt = tokenService.decodeJwt(jwtToken).getExpiresAt();

    return new AuthResponseDto(user, jwtToken, expiresAt);
  }

  private Optional<User> finUser(String email) {
    return userRepository.findUserByEmail(email);
  }

}
