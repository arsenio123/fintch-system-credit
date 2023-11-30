package com.malagueta.fintch.tables;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.ToString;


import java.time.LocalDateTime;

@Entity
@ToString
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Credito credito;
    private Double valor;
    private String descricao;

    @Timestamp
    private LocalDateTime enventDate;

    public Long getId() {
        return id;
    }

    public  Capital(){
        enventDate= LocalDateTime.now();
    }
    public Capital setId(Long id) {
        this.id = id;
        return this;
    }

    public Credito getCredito() {
        return credito;
    }

    public Capital setCredito(Credito credito) {
        this.credito = credito;
        return this;
    }

    public Double getValor() {
        return valor;
    }

    public Capital setValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Capital setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LocalDateTime getEnventDate() {
        return enventDate;
    }

    public Capital setEnventDate(LocalDateTime enventDate) {
        this.enventDate = enventDate;
        return this;
    }
}
