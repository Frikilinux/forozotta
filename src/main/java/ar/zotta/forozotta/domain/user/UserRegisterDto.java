package ar.zotta.forozotta.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDto(
                @NotBlank String name,
                @NotBlank @Email String email,
                @NotBlank String password) {

}
