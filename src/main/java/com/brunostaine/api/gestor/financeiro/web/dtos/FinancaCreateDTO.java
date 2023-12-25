package com.brunostaine.api.gestor.financeiro.web.dtos;

import com.brunostaine.api.gestor.financeiro.entities.enums.Categoria;
import com.brunostaine.api.gestor.financeiro.entities.enums.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancaCreateDTO {

    @NotBlank
    @Size(min = 3 ,max = 25)
    private String tipo;
    private BigDecimal valor;
    @NotBlank
    @Size(max = 25)
    private String categoria;
    @Size(max = 300)
    private String descricao;
}
