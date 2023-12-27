package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.tables.Credito;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Arsenio Malagueta
 */
public class CreditDTO {
    public static Credito convertToRow(CreditEntity creditEntity) {
        Credito credito= new Credito();
        credito.setAprovadoPOr(creditEntity.getAprovadoPOr());
        credito.setCliente(ClienteDTO.convertToRow(creditEntity.getCliente()));
        credito.setId(creditEntity.getId());
        credito.setBeginDate(creditEntity.getBeginDate());
        credito.setEstado(creditEntity.getEstado());
        credito.setDoDate(creditEntity.getDoDate());
        credito.setProducto(DTOProduct.convertToRow(creditEntity.getProducto()));
        credito.setCreatedBy(creditEntity.getCreatedBy());
        credito.setValor(creditEntity.getValor());
        credito.setProxima_Prestacao(creditEntity.getProxima_Prestacao());
        credito.setUpdateDate(creditEntity.getUpdateDate());
        credito.setJurus(creditEntity.getJurus());
        credito.setCreatedDate(creditEntity.getCreatDate());
        return credito;
    }

    public static CreditEntity convertToEntity(Credito creditoRow) {
        CreditEntity creditEntity= CreditEntity.builder()
                .id(creditoRow.getId())
                .cliente(ClienteDTO.convertToEntity(creditoRow.getCliente()))
                .proxima_Prestacao(creditoRow.getProxima_Prestacao())
                .doDate(creditoRow.getDoDate())
                .producto(DTOProduct.convertToEntity(creditoRow.getProducto()))
                .createdBy(creditoRow.getCreatedBy())
                .aprovadoPOr(creditoRow.getAprovadoPOr())
                .creatDate(creditoRow.getCreatedDate())
                .valor(creditoRow.getValor())
                .beginDate(creditoRow.getBeginDate())
                .jurus(creditoRow.getJurus())
                .build();
        return creditEntity;
    }

    public static List<CreditEntity> convertToEntitys(List<Credito> creditoRows) {
        List<CreditEntity> creditEntitys= new ArrayList<>();
        creditoRows.stream().forEach(credito -> {
                    creditEntitys.add(convertToEntity(credito));
        });
        return creditEntitys;
    }
}
