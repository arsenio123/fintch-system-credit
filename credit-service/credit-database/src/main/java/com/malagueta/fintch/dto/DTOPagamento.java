package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.PagamentoEntity;
import com.malagueta.fintch.tables.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class DTOPagamento {
    public static Pagamento convertToRow(PagamentoEntity pagamentoEntity) {
        if(pagamentoEntity==null)
            return null;
        Pagamento pagamento=new Pagamento();
        pagamento.setCapital(DTOCapital.convertToRow(pagamentoEntity.getCapital()));
        pagamento.setFormaPagamento(pagamentoEntity.getFormaPagamento());
        pagamento.setConta(pagamentoEntity.getConta());
        pagamento.setCreatedBay(pagamentoEntity.getCreatedBay());
        pagamento.setIntrest(DTOIntrest.convertToRow(pagamentoEntity.getIntrest()));
        pagamento.setId(pagamentoEntity.getId());
        pagamento.setValorPago(pagamentoEntity.getValorPago());
        pagamento.setDataDePagametno(pagamentoEntity.getDataDePagametno());
        pagamento.setPrestacao(DTOPrestacao.convertToRow(pagamentoEntity.getPrestacao()));
        return pagamento;
    }

    public static PagamentoEntity convertToEntity(Pagamento pagamento) {
        if(pagamento==null)
            return null;
        PagamentoEntity pagamentoEntity=PagamentoEntity
                .builder()
                .id(pagamento.getId())
                .formaPagamento(pagamento.getFormaPagamento())
                .capital(DTOCapital.convertToEntity(pagamento.getCapital()))
                .conta(pagamento.getConta())
                .intrest(DTOIntrest.convertToEntity(pagamento.getIntrest()))
                .prestacao(DTOPrestacao.convetToEntity(pagamento.getPrestacao()))
                .dataDePagametno(pagamento.getDataDePagametno())
                .createdBay(pagamento.getCreatedBay())
                .build();
        return pagamentoEntity;
    }

    public static List<PagamentoEntity> convertToEntitys(List<Pagamento> pagamentos) {
        if(pagamentos==null)
            return null;
        List<PagamentoEntity> pagamentoEntities=new ArrayList<PagamentoEntity>();
        pagamentos.stream().forEach(pagamento -> {
            pagamentoEntities.add(DTOPagamento.convertToEntity(pagamento));

        });
        return pagamentoEntities;
    }
}
