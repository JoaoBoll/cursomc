package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoriaService {

    @Autowired
    public CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
