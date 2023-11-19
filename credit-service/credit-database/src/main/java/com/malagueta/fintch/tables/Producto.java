package com.malagueta.fintch.tables;

import com.malagueta.fintch.domain_service.value.ProductoEstados;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Producto  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * taxa de juros a que o credito esta sugeito
     */
    private Double taxa;
    /**
     * valor minimo que se pode pedir no producto
     */
    private long capitalMin;
    /**
     * valor maximo que se pode pedir no producto
     */
    private long capitalMax;
    /**
     * Descricao do producto
     */
    private String descricao;
    /**
     * estado do producto
     */
    private ProductoEstados estado;

    /**
     * Intervalo em dias das prestacoes a pagar
     */
    private int intervaloPrestacao;

    public Long getId() {
        return id;
    }

    public Producto setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getTaxa() {
        return taxa;
    }

    public Producto setTaxa(Double taxa) {
        this.taxa = taxa;
        return this;
    }

    public long getCapitalMin() {
        return capitalMin;
    }

    public Producto setCapitalMin(long capitalMin) {
        this.capitalMin = capitalMin;
        return this;
    }

    public long getCapitalMax() {
        return capitalMax;
    }

    public Producto setCapitalMax(long capitalMax) {
        this.capitalMax = capitalMax;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Producto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProductoEstados getEstado() {
        return estado;
    }

    public Producto setEstado(ProductoEstados estado) {
        this.estado = estado;
        return this;
    }

      public int getIntervaloPrestacao() {
        return intervaloPrestacao;
    }

    public Producto setIntervaloPrestacao(int intervaloPrestacao) {
        this.intervaloPrestacao = intervaloPrestacao;
        return this;
    }
}
