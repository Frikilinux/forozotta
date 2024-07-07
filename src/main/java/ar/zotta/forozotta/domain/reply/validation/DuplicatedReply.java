package ar.zotta.forozotta.domain.reply.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.zotta.forozotta.domain.reply.Reply;
import ar.zotta.forozotta.domain.reply.ReplyRepository;
import ar.zotta.forozotta.domain.reply.ReplyTopicDto;
import jakarta.validation.ValidationException;

@Component
public class DuplicatedReply implements ReplyValidation<ReplyTopicDto> {
  @Autowired
  ReplyRepository replyRepository;

  @Override
  public void validate(ReplyTopicDto dto) {
    Optional<Reply> reply = replyRepository.checkDuplicatedReply(dto.topicId(), dto.message());

    if (reply.isPresent()) {
      throw new ValidationException("Ya esxiste una respuesta similar");
    }
  }

}
