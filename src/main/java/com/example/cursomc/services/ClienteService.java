package com.example.cursomc.services;

import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.dto.ClienteDto;
import com.example.cursomc.dto.ClienteNewDto;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public CidadeRepository cidadeRepository;

    @Autowired
    public EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


    public List<Cliente> findAll() {
        List<Cliente> categorias = clienteRepository.findAll();
        return categorias;
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return clienteRepository.save(obj);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);

        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDto obj) {
        return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
    }

    public Cliente fromDto(ClienteNewDto obj) {
        Cliente cli = new Cliente(null , obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), obj.getTipo());
        Optional<Cidade> cid = cidadeRepository.findById(obj.getCidadeId());
        Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid.get());

        cli.getEnderecos().add(end);
        cli.getTelefones().add(obj.getTelefone1());

        if (obj.getTelefone2() != null) {
            cli.getTelefones().add(obj.getTelefone2());
        }

        if (obj.getTelefone3() != null) {
            cli.getTelefones().add(obj.getTelefone3());
        }

        return cli;
    }
}
