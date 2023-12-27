package com.malagueta.fintch.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@Setter
@Getter
public class PagamentoEntity {

    private long id;

    private LocalDate dataDePagametno;
    private Long createdBay;
    private PrestacaoEntity prestacao;

    private long conta;
    private double valorPago;
    private String formaPagamento;
    private IntrestEntity intrest;
    private CapitalEntity capital;

}
