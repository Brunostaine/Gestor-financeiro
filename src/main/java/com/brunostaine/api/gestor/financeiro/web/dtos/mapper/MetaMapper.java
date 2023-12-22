package com.brunostaine.api.gestor.financeiro.web.dtos.mapper;

import com.brunostaine.api.gestor.financeiro.entities.Meta;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaCreateDTO;
import com.brunostaine.api.gestor.financeiro.web.dtos.MetaResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaMapper {

    public static Meta toMeta(MetaCreateDTO dto){
        return new ModelMapper().map(dto, Meta.class);
    }

    public static MetaResponseDTO toDto(Meta meta) {
        return new ModelMapper().map(meta, MetaResponseDTO.class);
    }

}
