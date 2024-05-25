package com.example.cursomc.domain;

import com.example.cursomc.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PagamentoComCartao.class, name = "pagamentoComCartao"),
        @JsonSubTypes.Type(value = PagamentoComBoleto.class, name = "pagamentoComBoleto")
})
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    @MapsId
    @OneToOne
    private Pedido pedido;

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estado = estadoPagamento == null ? null : estadoPagamento.getCod();
        this.pedido = pedido;
    }
}
