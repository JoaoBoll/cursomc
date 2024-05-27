package com.example.cursomc.repositories;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly=true)
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
            @Param("nome")String nome,
            @Param("categorias") List<Categoria> categorias,
            PageRequest pageRequest);

    @Transactional
    @Query("SELECT prod FROM Produto prod WHERE prod.nome LIKE %:nome%")
    List<Produto> findAllByName(@Param("nome") String nome);
}
