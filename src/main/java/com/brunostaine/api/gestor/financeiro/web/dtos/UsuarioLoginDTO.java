package com.brunostaine.api.gestor.financeiro.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioLoginDTO {
    @NotBlank
    @Email(message = "formato do e-mail inválido.", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;
    @NotBlank
    @Size(min = 6, max = 6)
    private String password;
}
