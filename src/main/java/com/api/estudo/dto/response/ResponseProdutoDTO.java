package com.api.estudo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProdutoDTO {

    private Long id;
    private String nome;
    private String foto;
    private String descricao;
    private String medida;
    private String preco;
}
