package ar.zotta.forozotta.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Email;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User registerUser(RegisterUserDTO registerUserDTO) {

    if (userRepository.findByEmail(registerUserDTO.email()).isPresent()) {
      throw new RuntimeException("El email ya existe");
    }

    User user = new User(registerUserDTO);

    // Reemplaza el password recibido con uno encryptado
    user.setPassword(passwordEncoder.encode(registerUserDTO.password()));

    return userRepository.save(user);

  }

}
