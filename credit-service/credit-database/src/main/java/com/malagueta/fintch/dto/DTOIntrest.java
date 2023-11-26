package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.tables.Intrest;
import org.jetbrains.annotations.NotNull;

public class DTOIntrest {
    public static Intrest convertToRow(@NotNull IntrestEntity intrestEntity) {
        Intrest intrestRow= new Intrest().setId(intrestEntity.getId())
                .setCredito(CreditDTO.convertToTable(intrestEntity.getCredito()))
                .setValor(intrestEntity.getValor())
                .setEnventDate(intrestEntity.getEnventDate());
        return intrestRow;

    }

    public static IntrestEntity convertToEntity(@NotNull Intrest intrestRow) {
        return IntrestEntity.builder()
                .credito(CreditDTO.convertToEntity(intrestRow.getCredito()))
                .id(intrestRow.getId())
                .enventDate(intrestRow.getEnventDate())
                .descricao(intrestRow.getDescricao())
                .valor(intrestRow.getValor()).build();
    }
}
