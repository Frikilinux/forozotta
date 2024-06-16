package ar.zotta.forozotta.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User registerUser(RegisterUserDto registerUserDto) {

    if (userRepository.findByEmail(registerUserDto.email()).isPresent()) {
      throw new RuntimeException("El email ya existe");
    }

    User user = new User(registerUserDto);

    // Reemplaza el password recibido con uno encryptado
    user.setPassword(passwordEncoder.encode(registerUserDto.password()));

    return userRepository.save(user);

  }

  public UserResponseDto userResponse(User user) {
    return new UserResponseDto(user);
  }

}
