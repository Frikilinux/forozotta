package ar.zotta.forozotta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.zotta.forozotta.domain.topic.CreateTopicDto;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicResponseDto;
import ar.zotta.forozotta.domain.topic.TopicService;
import ar.zotta.forozotta.domain.topic.UpdateTopicDto;
import ar.zotta.forozotta.domain.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @Autowired
  UserService userService;

  @PostMapping
  public ResponseEntity<TopicResponseDto> postTopic(@RequestBody @Valid CreateTopicDto createTopicDto) {

    Topic topic = topicService.createTopic(createTopicDto);

    TopicResponseDto topicResponseDto = new TopicResponseDto(topic);

    return ResponseEntity.status(201).body(topicResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<TopicResponseDto>> getTopics() {

    List<Topic> topics = topicService.getTopics();

    return ResponseEntity.ok(topics.stream().map(TopicResponseDto::new).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicResponseDto> getTopicById(@PathVariable Long id) {

    Topic topic = topicService.getTopicById(id);

    return ResponseEntity.ok(new TopicResponseDto(topic));

  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicResponseDto> updateTopic(@PathVariable Long id,
      @RequestBody UpdateTopicDto updateTopicDto) {

    Topic topic = topicService.getTopicById(id);
    topic.updateTopic(updateTopicDto);

    return ResponseEntity.ok(new TopicResponseDto(topic));
  }

}
