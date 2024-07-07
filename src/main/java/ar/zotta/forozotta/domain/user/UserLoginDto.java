package ar.zotta.forozotta.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
    @NotBlank(message = "Debe especificar un email.") @Email String email,
    @NotBlank(message = "Debe especificar una contraseña.") String password) {

}
