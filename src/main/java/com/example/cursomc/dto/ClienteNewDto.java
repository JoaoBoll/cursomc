package com.example.cursomc.dto;

import com.example.cursomc.enums.TipoCliente;
import lombok.Data;

@Data
public class ClienteNewDto {

    private String nome;
    private String email;
    private String  cpfOuCnpj;
    private TipoCliente tipo;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDto() {

    }

}
