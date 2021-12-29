package com.api.estudo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePartidoDTO {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sigla;

}
