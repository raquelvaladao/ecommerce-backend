package com.api.estudo.dto.mappers;

import com.api.estudo.dto.EnderecoDTO;
import com.api.estudo.entities.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class EnderecoMapper {

    public abstract Endereco fromDTO(EnderecoDTO dto);

    public abstract EnderecoDTO fromEntity(Endereco entity);
}
