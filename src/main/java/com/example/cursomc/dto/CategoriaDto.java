package com.example.cursomc.dto;

import com.example.cursomc.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDto (Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public CategoriaDto() {

    }

}
