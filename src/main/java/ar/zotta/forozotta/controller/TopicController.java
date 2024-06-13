package ar.zotta.forozotta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.topic.CreateTopicDTO;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicResponseDTO;
import ar.zotta.forozotta.domain.topic.TopicService;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.domain.user.UserResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @PostMapping
  public ResponseEntity<TopicResponseDTO> postTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO) {

    Topic res = topicService.createTopic(createTopicDTO);
    User author = res.getAuthor();

    TopicResponseDTO topicResponseDTO = new TopicResponseDTO(res.getId(), res.getTitle(), res.getMessage(),
        new UserResponseDTO(author.getId(), author.getName(), author.getEmail()), res.getCreatedAt());

    return ResponseEntity.status(201).body(topicResponseDTO);
  }
}
