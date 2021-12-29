package com.api.estudo.dto.mappers;


import com.api.estudo.dto.request.RequestPoliticoDTO;
import com.api.estudo.dto.request.RequestPutPoliticoDTO;
import com.api.estudo.dto.response.ResponsePoliticoDTO;
import com.api.estudo.entities.Politico;
import com.api.estudo.repositories.CargoRepository;
import com.api.estudo.repositories.PartidoRepository;
import com.api.estudo.repositories.PoliticoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(unmappedSourcePolicy = WARN,
        unmappedTargetPolicy = WARN,
        componentModel = "spring")
public abstract class PoliticoMapper {

    @Autowired
    PoliticoRepository politicoRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    CargoRepository cargoRepository;

    @Mapping(target = "endereco", source = "enderecoDTO")
    @Mapping(target = "partido", expression = "java(partidoRepository.findById(dto.getPartidoId()).get())")
    @Mapping(target = "cargo", expression = "java(cargoRepository.findByNome(dto.getCargoDTO().getNome()).get())")
    public abstract Politico fromDTO(RequestPoliticoDTO dto);

    @Mapping(target = "partidoDTO", source = "partido")
    @Mapping(target = "cargoDTO", source = "cargo")
    @Mapping(target = "foto", expression = "java(entity.getFotoImagePath())")
    @Mapping(target = "enderecoDTO", source = "endereco")
    public abstract ResponsePoliticoDTO fromEntity(Politico entity);

    @Mapping(target = "endereco", source = "enderecoDTO")
    @Mapping(target = "partido", expression = "java(partidoRepository.findById(dto.getPartidoId()).get())")
    @Mapping(target = "cargo", source = "cargoDTO")
    public abstract Politico toUpdatedEntity(RequestPutPoliticoDTO dto);

}
