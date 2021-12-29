package com.api.estudo.dto.mappers;


import com.api.estudo.dto.PartidoDTO;
import com.api.estudo.entities.Partido;
import com.api.estudo.repositories.PartidoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class PartidoMapper {

    @Autowired
    PartidoRepository partidoRepository;

    public abstract Partido fromDTO(PartidoDTO dto);

    public abstract PartidoDTO fromEntity(Partido entity);


}
