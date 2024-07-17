package ar.zotta.forozotta.domain.user;

import java.util.UUID;

public record UserResponseDto(Long id, String name, String email, UUID uuid) {
  public UserResponseDto(User user) {
    this(user.getId(), user.getName(), user.getEmail(), user.getUuid());
  }
}
