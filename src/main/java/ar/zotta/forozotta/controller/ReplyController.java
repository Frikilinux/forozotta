package ar.zotta.forozotta.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Respuestas")
@RestController
@RequestMapping("/replies")
public class ReplyController {

  @Autowired
  ReplyService replyService;

  @Operation(summary = "Crea una nueva respuesta para un tópico.")
  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  public ResponseEntity<ReplyResponseDto> replyTopic(@RequestBody @Valid ReplyTopicDto replyTopicDto,
      UriComponentsBuilder uriComponentsBuilder) {

    Reply reply = replyService.replyTopic(replyTopicDto);

    URI uri = uriComponentsBuilder.path("/replies/{id}").buildAndExpand(reply.getId()).toUri();

    return ResponseEntity.created(uri).body(new ReplyResponseDto(reply));
  }

  @Operation(summary = "Modifica una respuesta.")
  @SecurityRequirement(name = "bearer-key")
  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<ReplyResponseDto> updateReply(@PathVariable Long id,
      @RequestBody ReplyUpdateDto replyUpdateDto) {
    Reply reply = replyService.updateReply(id, replyUpdateDto);

    return ResponseEntity.ok().body(new ReplyResponseDto(reply));
  }

  @Operation(summary = "Marca una respuesta como solución")
  @SecurityRequirement(name = "bearer-key")
  @PutMapping("/{id}/solution")
  @Transactional
  public ResponseEntity<ReplyResponseDto> toggleSolution(@PathVariable Long id) {
    Reply reply = replyService.toggleSolution(id);

    return ResponseEntity.ok().body(new ReplyResponseDto(reply));
  }

  @Operation(summary = "Devuelve una respuesta.")
  @GetMapping("/{id}")
  public ResponseEntity<ReplyResponseDto> getReplyById(@PathVariable Long id) {
    Reply reply = replyService.getReplyById(id);

    return ResponseEntity.ok().body(new ReplyResponseDto(reply));
  }

  // Hard delete
  @Operation(summary = "Elimina una respuesta (hard delete).")
  @SecurityRequirement(name = "bearer-key")
  @DeleteMapping("/{id}")
  public ResponseEntity<ReplyResponseDto> deleteReply(@PathVariable Long id) {
    replyService.deleteRplyById(id);

    return ResponseEntity.noContent().build();
  }

}
