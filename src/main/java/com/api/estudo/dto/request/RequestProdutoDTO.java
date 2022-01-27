package com.api.estudo.dto.request;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestProdutoDTO {

    private String nome;
    private String descricao;
    private String medida;
    private BigDecimal preco;
    private Integer estoque;
}
