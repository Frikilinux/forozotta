package ar.zotta.forozotta.domain.reply;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.zotta.forozotta.domain.topic.Topic;
import ar.zotta.forozotta.domain.topic.TopicRepository;
import ar.zotta.forozotta.domain.user.User;
import ar.zotta.forozotta.infra.security.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@Service
public class ReplyService {
  @Autowired
  ReplyRepository replyRepository;

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  AuthService authService;

  public Reply replyTopic(ReplyTopicDto replyTopicDto) {
    Optional<Topic> topic = topicRepository.findById(replyTopicDto.topicId());

    User user = authService.getAutorizedUser();

    if (topic.isEmpty()) {
      throw new EntityNotFoundException("Topic no encontrado");
    }

    Reply reply = replyRepository.save(new Reply(replyTopicDto.message(), user, topic.get()));

    return reply;

  }

  public Reply updateReply(Long id, ReplyUpdateDto replyUpdateDto) {
    Optional<Reply> reply = replyRepository.findById(id);

    if (reply.isEmpty()) {
      throw new EntityNotFoundException("La respuesta no existe");
    }

    Reply newReply = reply.get();
    newReply.updateReply(replyUpdateDto);

    return newReply;

  }

}
