package com.brunostaine.api.gestor.financeiro.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MetaCreateDTO {
    @NotBlank
    @Size(min = 5, max = 100)
    private String title;
}
