package com.api.estudo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    @Column(name = "datavenda")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JsonIgnore
    private Usuario comprador;

    @Embedded
    private Endereco endereco;


}
