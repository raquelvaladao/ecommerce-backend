package com.api.estudo.entities;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@ToString
public class Endereco {
    private String rua;
    private String estado;
    private String cep;
    private String cidade;
    private String bairro;
}
