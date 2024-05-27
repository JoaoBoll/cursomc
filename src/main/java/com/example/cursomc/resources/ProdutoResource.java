package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.dto.CategoriaDto;
import com.example.cursomc.dto.ProdutoDto;
import com.example.cursomc.repositories.ProdutoRepository;
import com.example.cursomc.resources.utils.URL;
import com.example.cursomc.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Produto produto = produtoService.find(id);

        if (produto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDto dto) {
        Produto obj = produtoService.fromDto(dto);
        obj = produtoRepository.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        List<Produto> produto = produtoService.findAll(null);

        if (produto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(produto);

    }

//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Produto obj) {
//        obj.setId(id);
//        obj = produtoService.update(obj);
//        return ResponseEntity.noContent().build();
//    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDto>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecode = URL.decodeParam(nome);
        List<Integer> ids = null;
        if (!categorias.isBlank()) {
            ids = URL.decodeIntList(categorias);
        }
        Page<Produto> list = produtoService.findPage(nomeDecode, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDto> produtos = list.map(e -> new ProdutoDto(e));
        return ResponseEntity.ok().body(produtos);
    }



}
