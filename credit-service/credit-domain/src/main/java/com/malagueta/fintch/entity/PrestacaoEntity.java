package com.malagueta.fintch.entity;

import com.malagueta.fintch.domain_service.value.Estado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PrestacaoEntity {
    private long id;
    private LocalDate vencimento;
    private LocalDate dataPagamento;
    private CreditEntity credito;
    private long contaCreditada;
    private double valorCapitaPorPagar;
    private double jurusPago;
    private double capitalPago;
    private IntrestEntity intrest;
    private CapitalEntity capital;
    private Estado estado;


    public boolean equals(PrestacaoEntity prestacaoEntity) {
        return (this.id==prestacaoEntity.getId());

    }
}
