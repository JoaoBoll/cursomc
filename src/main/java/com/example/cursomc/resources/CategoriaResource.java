package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {

        Categoria categoria1 = new Categoria(1, "Informática");
        Categoria categoria2 = new Categoria(1, "Informática");

        List<Categoria> ls = new ArrayList<>();

        ls.add(categoria1);
        ls.add(categoria2);

        //testando

        ls.add(new Categoria(3, "TESTEEE"));
        return ls;
    }

}
