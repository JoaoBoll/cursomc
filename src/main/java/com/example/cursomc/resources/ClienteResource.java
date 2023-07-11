package com.example.cursomc.resources;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDto;
import com.example.cursomc.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Cliente cliente = clienteService.find(id);

        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(cliente);
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Cliente> list = clienteService.findAll();
        List<ClienteDto> cliente = list.stream().map(e -> new ClienteDto(e)).collect(Collectors.toList());
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cliente);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDto dto) {
        Cliente obj = clienteService.fromDto(dto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Cliente obj) {
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDto> cliente = list.map(e -> new ClienteDto(e));
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cliente);
    }


}
