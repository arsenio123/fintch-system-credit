package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.tables.Credito;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Arsenio Malagueta
 */
public class CreditDTO {
    public static Credito convertToTable(CreditEntity credit) {
        Credito credito= new Credito();
        credito.setId(credit.getId());
        return credito;
    }

    public static CreditEntity convertToEntity(Credito creditoRow) {
        CreditEntity creditEntity= CreditEntity.builder()
                .id(creditoRow.getId())
                .cliente(null)
                .proxima_Prestacao(creditoRow.getProxima_Prestacao())
                .doDate(ZonedDateTime.now()).build();
        return creditEntity;
    }

    public static List<CreditEntity> convertToEntity(List<Credito> creditoRows) {
        List<CreditEntity> creditEntitys= new ArrayList<>();
        creditoRows.stream().forEach(credito -> {
                    creditEntitys.add(convertToEntity(credito));
        });
        return creditEntitys;
    }
}
