package com.api.estudo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_politico")
public class Politico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String foto;

    private Integer projetos;
    private Integer processos;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Partido partido;

    private Boolean lider;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    private Cargo cargo;

    @Transient
    public String getFotoImagePath() {
        if (foto == null || id == null) return null;

        return "/politicos-photos/" + id + "/" + foto;
    }


}
