package com.api.estudo.dto.response;


import com.api.estudo.dto.CargoDTO;
import com.api.estudo.dto.EnderecoDTO;
import com.api.estudo.dto.PartidoDTO;
import com.api.estudo.dto.response.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePoliticoDTO {

    @JsonView(View.Admin.class)
    private Long id;

    private String nome;

    @JsonView(View.Admin.class)
    private String cpf;

    @JsonView(View.Admin.class)
    private String telefone;

    private String foto;
    private Boolean lider;
    private Integer projetos;
    private Integer processos;
    private CargoDTO cargoDTO;
    private PartidoDTO partidoDTO;

    @JsonView(View.Admin.class)
    private EnderecoDTO enderecoDTO;

}
