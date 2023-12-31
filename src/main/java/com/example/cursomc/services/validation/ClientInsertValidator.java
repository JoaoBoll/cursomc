package com.example.cursomc.services.validation;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteNewDto;
import com.example.cursomc.enums.TipoCliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.resources.exception.FieldMessage;
import com.example.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDto> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj","CPF Inválido"));
        }
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email","Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getFieldMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
