package ar.zotta.forozotta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {
  @GetMapping
  public ResponseEntity getTopics() {
    return ResponseEntity.ok("HELLO HELLO");
  }

}
