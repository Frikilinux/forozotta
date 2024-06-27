package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.reply.ReplyResponseDto;
import ar.zotta.forozotta.domain.reply.ReplyService;
import ar.zotta.forozotta.domain.reply.ReplyTopicDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/replies")
public class ReplyController {

  @Autowired
  ReplyService replyService;

  @PostMapping
  public ResponseEntity<ReplyResponseDto> replyTopic(@RequestBody @Valid ReplyTopicDto replyTopicDto,
      UriComponentsBuilder uriComponentsBuilder) {

    Reply reply = replyService.replyTopic(replyTopicDto);

    URI uri = uriComponentsBuilder.path("/replies/{id}").buildAndExpand(reply.getId()).toUri();

    return ResponseEntity.created(uri).body(new ReplyResponseDto(reply));
  }

}
