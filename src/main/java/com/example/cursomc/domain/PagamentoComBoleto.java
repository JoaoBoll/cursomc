package com.example.cursomc.domain;

import com.example.cursomc.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataVencimento;
    private Integer numeroDeParcelas;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataPagamento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoComBoleto() {

    }
}
