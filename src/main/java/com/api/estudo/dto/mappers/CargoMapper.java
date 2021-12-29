package com.api.estudo.dto.mappers;

import com.api.estudo.dto.CargoDTO;
import com.api.estudo.entities.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class CargoMapper {

    public abstract Cargo fromDTO(CargoDTO dto);

    public abstract CargoDTO fromEntity(Cargo entity);
}
