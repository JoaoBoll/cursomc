package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.services.CategoriaService;
import com.example.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cliente cliente = clienteService.buscar(id);

        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(cliente);
    }

}
