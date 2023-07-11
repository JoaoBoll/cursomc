package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDto;
import com.example.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDto> categorias = list.stream().map(e -> new CategoriaDto(e)).collect(Collectors.toList());
        if (categorias == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(categorias);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria categoria = categoriaService.find(id);
        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria obj) {
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDto> categorias = list.map(e -> new CategoriaDto(e));
        if (categorias == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(categorias);
    }


}
