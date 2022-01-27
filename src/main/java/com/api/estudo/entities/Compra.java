package com.api.estudo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_compra")
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Usuario comprador;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "compra_id")
    private List<ItemCompra> itensCompra;

    @Column(name = "valortotal")
    private BigDecimal valorTotal;

    public Compra() {
        this.itensCompra = new ArrayList<>();
    }

    public Compra(Long id, Usuario comprador, List<ItemCompra> itensCompra, BigDecimal valorTotal) {
        this.id = id;
        this.comprador = comprador;
        this.itensCompra = itensCompra;
        this.valorTotal = valorTotal;
    }


}
