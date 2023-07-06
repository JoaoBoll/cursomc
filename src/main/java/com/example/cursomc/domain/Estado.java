package com.example.cursomc.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    public Estado() {

    }

    public Estado(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }
}
