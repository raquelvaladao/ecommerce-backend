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
public class EnderecoDTO {
    public static final String CAMPO_VAZIO_WARNING = "Campo não pode ser vazio";

    @Size(min = 4, max = 20)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String logradouro;

    @Size(max = 20, message = "Não pode ter mais que 20 caracteres.")
    private String complemento;

    @Size(max = 10)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String numero;

    @Size(min = 4, max = 20)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String cep;
}
