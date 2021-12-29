package com.api.estudo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUsuarioDetalhesDTO {

    private String nome;
    private String login;
    private String perfil;
}
