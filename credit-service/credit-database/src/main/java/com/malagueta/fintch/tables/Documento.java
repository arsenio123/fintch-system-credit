package com.malagueta.fintch.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tipo;
    private String numero;
    private LocalDate dataValidade;
    private LocalDate dataEmissao;
    @JsonIgnore
    @OneToOne
    private Cliente client;
}
