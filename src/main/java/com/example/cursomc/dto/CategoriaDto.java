package com.example.cursomc.dto;

import com.example.cursomc.domain.Categoria;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    public CategoriaDto (Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

}
