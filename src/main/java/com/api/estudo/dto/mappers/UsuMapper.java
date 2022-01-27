package com.api.estudo.dto.mappers;


import com.api.estudo.dto.request.RequestUsuarioDTO;
import com.api.estudo.dto.response.ResponseUsuarioDetalhesDTO;
import com.api.estudo.dto.response.ResponseUsuarioDTO;
import com.api.estudo.entities.Usuario;
import com.api.estudo.repositories.PerfilRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public abstract class UsuMapper {

    @Autowired
    PerfilRepository perfilRepository;

    public abstract ResponseUsuarioDTO fromEntity(Usuario entity);

    @Mapping(target = "perfil", expression = "java(perfilRepository.findById(1L).get())")
    public abstract Usuario fromDTO(RequestUsuarioDTO dto);

    @Mapping(target = "perfil", expression = "java(entity.getPerfil().getNome())")
    public abstract ResponseUsuarioDetalhesDTO toResponseDetalhes(Usuario entity);

}
