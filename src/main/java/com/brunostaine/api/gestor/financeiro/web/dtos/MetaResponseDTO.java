package com.brunostaine.api.gestor.financeiro.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaResponseDTO {

    private String id;
    private String title;
    private String status;
}
