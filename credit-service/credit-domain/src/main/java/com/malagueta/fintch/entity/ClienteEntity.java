package com.malagueta.fintch.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class ClienteEntity {


    private long id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer rendimento;

    private String morada;

    private String telefone;

    private String email;


    private String idDoc;

    private  Setor setor;

    private String numberDoc;

    public enum Setor{
        PRIVADO("PRIVADO"),PUBLICO("PUBLICO");
        private final String value;


        Setor(final String value) {
            this.value=value;
        }


    }
}
