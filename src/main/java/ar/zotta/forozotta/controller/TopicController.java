package ar.zotta.forozotta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.topic.CreateTopicDto;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicResponseDto;
import ar.zotta.forozotta.domain.topic.TopicService;
import ar.zotta.forozotta.domain.user.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @Autowired
  UserService userService;

  @PostMapping
  public ResponseEntity<TopicResponseDto> postTopic(@RequestBody @Valid CreateTopicDto createTopicDTO) {

    Topic topic = topicService.createTopic(createTopicDTO);

    TopicResponseDto topicResponseDTO = new TopicResponseDto(topic);

    return ResponseEntity.status(201).body(topicResponseDTO);
  }

  @GetMapping
  public ResponseEntity<List<TopicResponseDto>> getTopics() {
    List<Topic> topics = topicService.getTopics();

    return ResponseEntity.ok(topics.stream().map(TopicResponseDto::new).toList());
  }

}
