package ar.zotta.forozotta.domain.topic;

import java.time.LocalDateTime;

import ar.zotta.forozotta.domain.user.UserResponseDTO;

public record TopicResponseDTO(Long id, String title, String message, UserResponseDTO author, LocalDateTime createdAt) {
}
