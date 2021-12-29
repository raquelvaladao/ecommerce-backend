package com.api.estudo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {

    public static final String CAMPO_VAZIO_WARNING = "Campo não pode ser vazio";

    @Size(min = 4, max = 60)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String nome;

    @Size(max = 20)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String sigla;
}
