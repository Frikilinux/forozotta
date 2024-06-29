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

    if (authService.checkOwner(newReply.getAuthor().getId())) {
      throw new ValidationException("El propietario de la respuesta no es igual al usuario logueado.");
    }

    newReply.updateReply(replyUpdateDto);

    return newReply;

  }

  public Reply getReplyById(Long id) {
    Optional<Reply> reply = replyRepository.findById(id);

    if (reply.isEmpty()) {
      throw new EntityNotFoundException("La respuesta no existe");
    }

    return reply.get();

  }

  public Reply toggleSolution(Long id) {
    Optional<Reply> reply = replyRepository.findById(id);

    if (reply.isEmpty()) {
      throw new EntityNotFoundException("La respuesta no existe");
    }

    Reply newReply = reply.get();

    Optional<Topic> topic = topicRepository.findById(newReply.getTopic().getId());

    if (topic.isEmpty()) {
      throw new EntityNotFoundException("Topic no encontrado");
    }

    if (authService.checkOwner(topic.get().getAuthor().getId())) {
      throw new ValidationException("Solo el dueño el topic puede cambiar el estado de solution");
    }

    newReply.toggleSolution(newReply);

    return newReply;

  }

  public void deleteRplyById(Long id) {
    Optional<Reply> reply = replyRepository.findById(id);

    if (reply.isEmpty()) {
      throw new EntityNotFoundException("La respuesta no existe");
    }

    if (authService.checkOwner(reply.get().getAuthor().getId())) {
      throw new ValidationException("El propietario de la respuesta no es igual al usuario logueado.");
    }

    replyRepository.deleteById(id);

  }
}
