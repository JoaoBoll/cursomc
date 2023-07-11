package com.example.cursomc.dto;

import com.example.cursomc.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDto(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public ClienteDto() {

    }

}
