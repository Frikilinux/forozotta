package ar.zotta.forozotta.domain.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.reply.ReplyRepository;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicRepository;
import ar.zotta.forozotta.domain.user.validation.UserValidation;
import ar.zotta.forozotta.infra.security.AuthResponseDto;
import ar.zotta.forozotta.infra.security.TokenService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  List<UserValidation<UserRegisterDto>> userValidations;

  public User registerUser(UserRegisterDto registerUserDto) {

    userValidations.forEach(uv -> uv.validate(registerUserDto));

    User user = new User(registerUserDto);

    // Reemplaza el password recibido con uno encryptado
    user.setPassword(passwordEncoder.encode(registerUserDto.password()));

    return userRepository.save(user);

  }

  public AuthResponseDto userAuth(UserLoginDto userLoginDto) {

    Optional<User> user = userRepository.findUserByEmail(userLoginDto.email());

    if (user.isEmpty()) {
      throw new EntityNotFoundException("Usuario no encontrado");
    }

    Authentication authRequestToken = new UsernamePasswordAuthenticationToken(userLoginDto.email(),
        userLoginDto.password());

    Authentication userAuthenticated = authenticationManager.authenticate(authRequestToken);

    String jwtToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());

    // User user = (User) userAuthenticated.getPrincipal();

    Date expiresAt = tokenService.decodeJwt(jwtToken).getExpiresAt();

    return new AuthResponseDto(user.get(), jwtToken, expiresAt);
  }

  public Page<Topic> getUserTopics(Long userId, Pageable pageable) {

    Optional<Page<Topic>> topics = topicRepository.findUserTopics(userId, pageable);

    if (topics.get().getTotalElements() <= 0) {
      throw new EntityNotFoundException("No se encotraron topicos para este usuario");
    }

    return topics.get();

  }

  public Page<Reply> getUserReplies(Long userId, Pageable pageable) {
    Optional<Page<Reply>> replies = replyRepository.getUserReplies(userId, pageable);

    if (replies.get().getTotalElements() <= 0) {
      throw new EntityNotFoundException("No se encontraron respuestas para este usuario");
    }

    return replies.get();
  }

}
