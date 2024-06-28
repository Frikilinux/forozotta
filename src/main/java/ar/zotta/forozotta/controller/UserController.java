package ar.zotta.forozotta.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.user.UserRegisterDto;
import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDto;
import ar.zotta.forozotta.domain.user.UserService;
import ar.zotta.forozotta.domain.user.UserTopicListResponseDto;
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

  @GetMapping("/{id}/topics")
  public ResponseEntity<List<UserTopicListResponseDto>> getUserTopics(@PathVariable Long id) {

    List<Topic> topics = userService.getUserTopics(id);

    return ResponseEntity.ok(topics.stream().map(UserTopicListResponseDto::new).toList());
  }

  @GetMapping("/{id}/replies")
  public ResponseEntity<List<UserRepliesListResponseDto>> getUserReplies(@PathVariable Long id) {

    List<Reply> replies = userService.getUserReplies(id);

    return ResponseEntity.ok(replies.stream().map(UserRepliesListResponseDto::new).toList());
  }
}
