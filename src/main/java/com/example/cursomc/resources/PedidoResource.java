package com.example.cursomc.resources;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Pedido pedido = pedidoService.find(id);

        if (pedido == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(pedido);
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Pedido obj) {
        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Pedido obj) {
        obj.setId(id);
        obj = pedidoService.update(obj);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Pedido> categorias = pedidoService.findAll();
        if (categorias == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(categorias);
    }


}
