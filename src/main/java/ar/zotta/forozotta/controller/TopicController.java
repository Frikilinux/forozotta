package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.topic.TopicCreateDto;
import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicCreateResponseDto;
import ar.zotta.forozotta.domain.topic.TopicListResponseDto;
import ar.zotta.forozotta.domain.topic.TopicResponseDto;
import ar.zotta.forozotta.domain.topic.TopicService;
import ar.zotta.forozotta.domain.topic.UpdateTopicDto;
import ar.zotta.forozotta.domain.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @Autowired
  UserService userService;

  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  public ResponseEntity<TopicCreateResponseDto> createTopic(@RequestBody @Valid TopicCreateDto createTopicDto,
      UriComponentsBuilder uriComponentsBuilder) {

    Topic topic = topicService.createTopic(createTopicDto);

    // TopicResponseDto topicResponseDto = new TopicResponseDto(topic);
    TopicCreateResponseDto topicCreateResponseDto = new TopicCreateResponseDto(topic);

    URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

    return ResponseEntity.created(uri).body(topicCreateResponseDto);
  }

  @GetMapping
  public ResponseEntity<Page<TopicListResponseDto>> getTopics(Pageable pageable) {

    Page<Topic> topics = topicService.getTopics(pageable);

    return ResponseEntity.ok(topics.map(TopicListResponseDto::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicResponseDto> getTopicById(@PathVariable Long id) {

    Topic topic = topicService.getTopicById(id);

    return ResponseEntity.ok(new TopicResponseDto(topic));

  }

  @SecurityRequirement(name = "bearer-key")
  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicResponseDto> updateTopic(@PathVariable Long id,
      @RequestBody UpdateTopicDto updateTopicDto) {

    Topic topic = topicService.updateTopic(id, updateTopicDto);

    return ResponseEntity.ok(new TopicResponseDto(topic));
  }

  // Hard delete
  @SecurityRequirement(name = "bearer-key")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTopic(@PathVariable Long id) {
    topicService.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
