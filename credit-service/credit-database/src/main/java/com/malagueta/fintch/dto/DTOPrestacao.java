package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.tables.Prestacao;

import java.util.ArrayList;
import java.util.List;

public class DTOPrestacao {
    public static Prestacao convertToRow(PrestacaoEntity prestacaoEntity) {
        if(prestacaoEntity ==null)
        return null;
        Prestacao prestacao=new Prestacao();
        prestacao.setCredito(CreditDTO.convertToRow(prestacaoEntity.getCredito()));
        prestacao.setId(prestacaoEntity.getId());
        prestacao.setCapitalPago(prestacaoEntity.getCapitalPago());
        prestacao.setEstado(prestacaoEntity.getEstado());
        prestacao.setVencimento(prestacaoEntity.getVencimento());
        prestacao.setDataPagamento(prestacaoEntity.getDataPagamento());
        prestacao.setIntrest(DTOIntrest.convertToRow(prestacaoEntity.getIntrest()));
        prestacao.setContaCreditada(prestacaoEntity.getContaCreditada());
        prestacao.setValorCapitaPorPagar(prestacaoEntity.getValorCapitaPorPagar());
        prestacao.setCapital(DTOCapital.convertToRow(prestacaoEntity.getCapital()));

        return prestacao;
    }

    public static PrestacaoEntity convetToEntity(Prestacao prestacao) {
        if(prestacao==null)
            return null;
        PrestacaoEntity prestacaoEntity=new PrestacaoEntity();
        prestacaoEntity.setCredito(CreditDTO.convertToEntity(prestacao.getCredito()));
        prestacaoEntity.setId(prestacao.getId());
        prestacaoEntity.setCapitalPago(prestacao.getCapitalPago());
        prestacaoEntity.setEstado(prestacao.getEstado());
        prestacaoEntity.setVencimento(prestacao.getVencimento());
        prestacaoEntity.setDataPagamento(prestacao.getDataPagamento());
        prestacaoEntity.setIntrest(DTOIntrest.convertToEntity(prestacao.getIntrest()));
        prestacaoEntity.setContaCreditada(prestacao.getContaCreditada());
        prestacaoEntity.setValorCapitaPorPagar(prestacao.getValorCapitaPorPagar());

        return prestacaoEntity;
    }

    public static List<PrestacaoEntity> convetToEntity(List<Prestacao> prestacoes) {
        List<PrestacaoEntity> listPrestacoes= new ArrayList<>();
        if(prestacoes==null)
            return listPrestacoes;
        prestacoes.stream().forEach(prestacao -> {
            listPrestacoes.add(DTOPrestacao.convetToEntity(prestacao));
        });
        return listPrestacoes;
    }
}
