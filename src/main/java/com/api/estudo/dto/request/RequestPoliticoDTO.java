package com.api.estudo.dto.request;


import com.api.estudo.dto.CargoDTO;
import com.api.estudo.dto.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPoliticoDTO {

    public static final String CAMPO_VAZIO_WARNING = "Campo não pode ser vazio";

    public static final String FORMATO_TELEFONE = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$";

    @Size(min = 4, max = 60)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    private String nome;

    @Size(min = 11, max = 15)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    @CPF(message = "Deve ser no formato de CPF, xxx.xxx.xxx-xx")
    private String cpf;

    @Size(min = 11, max = 20)
    @NotEmpty(message = CAMPO_VAZIO_WARNING)
    @Pattern(regexp = FORMATO_TELEFONE,
            message = "O formato do telefone é (xx) 9xxxx-xxxx")
    private String telefone;

    @NotNull(message = CAMPO_VAZIO_WARNING)
    private Boolean lider;

    @NotNull(message = CAMPO_VAZIO_WARNING)
    @Positive
    private Long partidoId;

    @NotNull(message = CAMPO_VAZIO_WARNING)
    private EnderecoDTO enderecoDTO;

    @NotNull(message = CAMPO_VAZIO_WARNING)
    private CargoDTO cargoDTO;

    @PositiveOrZero
    @NotNull(message = CAMPO_VAZIO_WARNING)
    private Integer projetos;

    @PositiveOrZero
    @NotNull(message = CAMPO_VAZIO_WARNING)
    private Integer processos;

}
