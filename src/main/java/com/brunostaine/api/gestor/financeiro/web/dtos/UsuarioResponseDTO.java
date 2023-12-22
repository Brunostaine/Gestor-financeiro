package com.brunostaine.api.gestor.financeiro.web.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDTO {

    private UUID id;
    private String nome;
    private String username;
    private String role;
}
