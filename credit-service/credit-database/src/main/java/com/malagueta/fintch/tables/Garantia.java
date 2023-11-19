package com.malagueta.fintch.tables;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Garantia  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String docB64;
    private String descricao;

    @ManyToOne
    private Credito credito_id;

    private enum estado{};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Garantia setDocB64(String docB64) {
        this.docB64 = docB64;
        return this;
    }

    public Garantia setCredito_id(Credito credito_id) {
        this.credito_id = credito_id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
