package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.topic.CreateTopicDto;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicResponseDto;
import ar.zotta.forozotta.domain.topic.TopicService;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @PostMapping
  public ResponseEntity<TopicResponseDto> postTopic(@RequestBody @Valid CreateTopicDto createTopicDTO) {

    Topic res = topicService.createTopic(createTopicDTO);
    User author = res.getAuthor();

    TopicResponseDto topicResponseDTO = new TopicResponseDto(res.getId(), res.getTitle(), res.getMessage(),
        new UserResponseDto(author.getId(), author.getName(), author.getEmail()), res.getCreatedAt());

    return ResponseEntity.status(201).body(topicResponseDTO);
  }
}
