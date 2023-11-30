package com.malagueta.fintch.tables;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malagueta.fintch.entity.ClienteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Entity
@Getter
@Setter
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer rendimento;

    private String morada;

    private String telefone;

    private String email;


    private String idDoc;

    private ClienteEntity.Setor setor;

    @Column(unique = true)
    private String numberDoc;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Documento> documento;



/*    public long getId() {
        return id;
    }

    public Cliente setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getRendimento() {
        return rendimento;
    }

    public void setRendimento(Integer rendimento) {
        this.rendimento = rendimento;
    }

    public String getMorada() {
        return morada;
    }

    public Cliente setMorada(String morada) {
        this.morada = morada;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public Cliente setIdDoc(String idDoc) {
        this.idDoc = idDoc;
        return this;
    }

    public String getNumberDoc() {
        return numberDoc;
    }


    public Cliente setNumberDoc(String numberDoc) {
        this.numberDoc = numberDoc;
        return this;
    }

    public Set<Documento> getDocumento() {
        return documento;
    }

    public Cliente setDocumento(Set<Documento> documento) {
        this.documento = documento;
        return this;
    }
*/
}
