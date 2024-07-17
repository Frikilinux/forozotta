package ar.zotta.forozotta.infra.security;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import ar.zotta.forozotta.domain.user.User;

public record AuthResponseDto(
    Long id, String email,
    String name,
    String access_token, String token_type,
    LocalDateTime expires_date,
    UUID uuid) {

  public AuthResponseDto(User user, String token, Date espiresAt) {
    this(user.getId(), user.getEmail(), user.getName(), token, "Bearer",
        new java.sql.Timestamp(espiresAt.getTime()).toLocalDateTime(), user.getUuid());
  }
}
