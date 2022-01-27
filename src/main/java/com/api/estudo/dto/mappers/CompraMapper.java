package com.api.estudo.dto.mappers;

import com.api.estudo.dto.response.ResponseCompraDTO;
import com.api.estudo.entities.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class CompraMapper {

    public abstract ResponseCompraDTO fromEntity(Compra entity);
}
