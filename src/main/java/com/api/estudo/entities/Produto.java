package com.api.estudo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String foto;
    private String medida;
    private BigDecimal preco;
    private Integer estoque;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JsonIgnore
    private Usuario dono;
}
