package ar.zotta.forozotta.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
    @NotBlank String name,
    @Email String email,
    @NotBlank String password) {

}
