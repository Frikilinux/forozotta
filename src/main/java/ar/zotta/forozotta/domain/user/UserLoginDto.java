package ar.zotta.forozotta.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(@NotBlank String email, @NotBlank String password) {

}
