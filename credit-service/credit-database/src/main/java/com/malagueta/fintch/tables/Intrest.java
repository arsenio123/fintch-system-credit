package com.malagueta.fintch.tables;


import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
public class Intrest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Credito credito;
    private Double valor;
    private String descricao;

    private LocalDateTime enventDate;

    public Long getId() {
        return id;
    }

    public  Intrest(){
        enventDate= LocalDateTime.now();
    }
    public Intrest setId(Long id) {
        this.id = id;
        return this;
    }

    public Credito getCredito() {
        return credito;
    }

    public Intrest setCredito(Credito credito) {
        this.credito = credito;
        return this;
    }

    public Double getValor() {
        return valor;
    }

    public Intrest setValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Intrest setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LocalDateTime getEnventDate() {
        return enventDate;
    }

    public Intrest setEnventDate(LocalDateTime enventDate) {
        this.enventDate = enventDate;
        return this;
    }
}
