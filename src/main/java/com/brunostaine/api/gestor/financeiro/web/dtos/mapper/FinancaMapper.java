package com.brunostaine.api.gestor.financeiro.web.dtos.mapper;

import com.brunostaine.api.gestor.financeiro.entities.Financa;
import com.brunostaine.api.gestor.financeiro.entities.Meta;
import com.brunostaine.api.gestor.financeiro.web.dtos.FinancaCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.FinancaResponseDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinancaMapper {

    public static Financa toFinanca(FinancaCreateDTO dto){
        return new ModelMapper().map(dto, Financa.class);
    }

    public static FinancaResponseDTO toDto(Financa financa) {
        return new ModelMapper().map(financa, FinancaResponseDTO.class);
    }
}
