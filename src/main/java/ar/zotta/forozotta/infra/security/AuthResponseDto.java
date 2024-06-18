package ar.zotta.forozotta.infra.security;

import java.util.Date;

import ar.zotta.forozotta.domain.user.User;

public record AuthResponseDto(Long id, String email, String access_token, String token_type, Date expires_date) {
  public AuthResponseDto(User user, String token, Date espiresAt) {
    this(user.getId(), user.getEmail(), token, "Bearer", espiresAt);
  }
}
