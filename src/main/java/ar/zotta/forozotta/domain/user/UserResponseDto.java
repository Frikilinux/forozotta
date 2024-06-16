package ar.zotta.forozotta.domain.user;

public record UserResponseDto(Long id, String name, String email) {
  public UserResponseDto(User user) {
    this(user.getId(), user.getName(), user.getEmail());
  }
}
