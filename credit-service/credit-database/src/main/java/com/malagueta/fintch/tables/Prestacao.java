package com.malagueta.fintch.tables;

import com.malagueta.fintch.domain_service.value.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Entity
public class Prestacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate vencimento;
    private LocalDate dataPagamento;

    //@JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)

    private Credito credito;
    private long contaCreditada;
    private double valorCapitaPorPagar;
    private double jurusPago;
    private double capitalPago;

    @Getter
    @Setter
    @OneToOne
    private Intrest intrest;

    @Getter
    @Setter
    @OneToOne
    private Capital capital;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public long getId() {
        return id;
    }

    public Prestacao setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public Prestacao setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
        return this;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public Prestacao setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public Credito getCredito() {
        return credito;
    }

    public Prestacao setCredito(Credito credito) {
        this.credito = credito;
        return this;
    }

    public long getContaCreditada() {
        return contaCreditada;
    }

    public Prestacao setContaCreditada(long contaCreditada) {
        this.contaCreditada = contaCreditada;
        return this;
    }

    public double getValorCapitaPorPagar() {
        return valorCapitaPorPagar;
    }

    public Prestacao setValorCapitaPorPagar(double valorCapitaPorPagar) {
        this.valorCapitaPorPagar = valorCapitaPorPagar;
        return this;
    }

    public double getJurusPago() {
        return jurusPago;
    }

    public Prestacao setJurusPago(double jurusPago) {
        this.jurusPago = jurusPago;
        return this;
    }

    public double getCapitalPago() {
        return capitalPago;
    }

    public Prestacao setCapitalPago(double capitalPago) {
        this.capitalPago = capitalPago;
        return this;
    }

    public Prestacao setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public Estado getEstado() {
        return estado;
    }
}

