package com.example.cursomc.dto;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoDto {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Double preco;

    private List<Categoria> categorias;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
        categorias = obj.getCategorias();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
