package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.user.UserRegisterDto;
import ar.zotta.forozotta.domain.user.UserRepliesListResponseDto;
import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDto;
import ar.zotta.forozotta.domain.user.UserService;
import ar.zotta.forozotta.domain.user.UserTopicListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Registra un nuevo usuario.")
  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRegisterDto registerUserDto,
      UriComponentsBuilder uriComponentsBuilder) {

    User user = userService.registerUser(registerUserDto);

    URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).body(new UserResponseDto(user));
  }

  @Operation(summary = "Devuelve los tópicos de un usuario.")
  @GetMapping("/{id}/topics")
  public ResponseEntity<Page<UserTopicListResponseDto>> getUserTopics(@PathVariable Long id, Pageable pageable) {

    Page<Topic> topics = userService.getUserTopics(id, pageable);

    return ResponseEntity.ok(topics.map(UserTopicListResponseDto::new));
  }

  @Operation(summary = "Devuelve las respuestas de un usuario.")
  @GetMapping("/{id}/replies")
  public ResponseEntity<Page<UserRepliesListResponseDto>> getUserReplies(@PathVariable Long id, Pageable pageable) {

    Page<Reply> replies = userService.getUserReplies(id, pageable);

    return ResponseEntity.ok(replies.map(UserRepliesListResponseDto::new));
  }
}
