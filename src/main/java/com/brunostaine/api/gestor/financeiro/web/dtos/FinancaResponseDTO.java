package com.brunostaine.api.gestor.financeiro.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancaResponseDTO {

    private UUID id;
    private String tipo;
    private BigDecimal valor;
    private String categoria;

}
