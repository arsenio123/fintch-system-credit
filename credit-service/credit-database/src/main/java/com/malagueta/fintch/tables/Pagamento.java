package com.malagueta.fintch.tables;

import jakarta.persistence.*;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@ToString
@Entity
public class Pagamento implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private
    LocalDate dataDePagametno;

    //@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    //private User createdBay;
    private Long createdBay;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="prestacao_id")
    private Prestacao prestacao;

    private long conta;
    private double valorPago;
    private String formaPagamento;

    @OneToOne
    private Intrest intrest;

    @OneToOne
    private Capital capital;

    public long getId() {
        return id;
    }

    public Pagamento setId(long id) {
        this.id = id;
        return this;
    }

    public
    LocalDate getDataDePagametno() {
        return dataDePagametno;
    }

    public Pagamento setDataDePagametno(
            LocalDate dataDePagametno) {
        this.dataDePagametno = dataDePagametno;
        return this;
    }

    public Long getCreatedBay() {
        return createdBay;
    }

    public Pagamento setCreatedBay(Long createdBay) {
        this.createdBay = createdBay;
        return this;
    }

    public long getConta() {
        return conta;
    }

    public Pagamento setConta(long conta) {
        this.conta = conta;
        return this;
    }

    public double getValorPago() {
        return valorPago;
    }

    public Pagamento setValorPago(double valorPago) {
        this.valorPago = valorPago;
        return this;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Pagamento setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public Prestacao getPrestacao() {
        return prestacao;
    }

    public Pagamento setPrestacao(Prestacao prestacao) {
        this.prestacao = prestacao;
        return this;
    }

    public Intrest getIntrest() {
        return intrest;
    }

    public Pagamento setIntrest(Intrest intrest) {
        this.intrest = intrest;
        return this;
    }

    public Capital getCapital() {
        return capital;
    }

    public Pagamento setCapital(Capital capital) {
        this.capital = capital;
        return this;
    }
}
