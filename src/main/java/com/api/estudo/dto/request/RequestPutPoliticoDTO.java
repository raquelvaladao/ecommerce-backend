package com.api.estudo.dto.request;

import com.api.estudo.dto.CargoDTO;
import com.api.estudo.dto.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPutPoliticoDTO {
        public static final String CAMPO_VAZIO_WARNING = "Campo não pode ser vazio";

        @Size(min = 4, max = 20)
        @NotEmpty(message = CAMPO_VAZIO_WARNING)
        private String nome;

        @NotEmpty(message = CAMPO_VAZIO_WARNING)
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$",
                message = "O formato do telefone é (xx) 9xxxx-xxxx")
        private String telefone;

        private Boolean lider;
        private Long partidoId;
        private EnderecoDTO enderecoDTO;
        private CargoDTO cargoDTO;

        private Integer projetos;
        private Integer processos;
}
