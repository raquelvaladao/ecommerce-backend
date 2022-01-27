package com.api.estudo.dto.mappers;

import com.api.estudo.dto.request.RequestProdutoDTO;
import com.api.estudo.dto.response.ResponseProdutoDTO;
import com.api.estudo.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class ProdutoMapper {

    public abstract Produto fromDTO(RequestProdutoDTO dto);

    public abstract ResponseProdutoDTO fromEntity(Produto entity);
}
