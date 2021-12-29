package com.api.estudo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_perfil")
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1964291035074919787L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Perfil(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return getNome();
    }
}
