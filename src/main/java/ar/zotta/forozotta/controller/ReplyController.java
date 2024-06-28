package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.reply.ReplyResponseDto;
import ar.zotta.forozotta.domain.reply.ReplyService;
import ar.zotta.forozotta.domain.reply.ReplyTopicDto;
import ar.zotta.forozotta.domain.reply.ReplyUpdateDto;
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

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable Long id,
      @RequestBody ReplyUpdateDto replyUpdateDto) {
    Reply reply = replyService.updateReply(id, replyUpdateDto);

    return ResponseEntity.ok().body(new ReplyResponseDto(reply));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable Long id) {
    Reply reply = replyService.getReplyById(id);

    return ResponseEntity.ok().body(new ReplyResponseDto(reply));
  }

}
