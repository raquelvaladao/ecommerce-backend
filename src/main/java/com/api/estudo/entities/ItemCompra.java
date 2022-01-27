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
@Table(name = "tb_itemcompra")
public class ItemCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Compra compra;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    @Column(name = "valorunitario")
    private BigDecimal valorUnitario;

    @Column(name = "valortotal")
    private BigDecimal valorTotal;

}
