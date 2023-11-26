package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.tables.Capital;
import org.jetbrains.annotations.NotNull;

public class DTOCapital {
    public static CapitalEntity convertToEntity(@NotNull  Capital capital) {
        CapitalEntity capitalEntity=CapitalEntity.builder()
                .id(capital.getId())
                .credito(CreditDTO.convertToEntity(capital.getCredito()))
                .descricao(capital.getDescricao())
                .valor(capital.getValor())
                .enventDate(capital.getEnventDate())
                .build();
        return capitalEntity;
    }

    public static Capital convertToCapital(@NotNull CapitalEntity capitalEntity){
        Capital capital=new Capital();
        capital.setCredito(CreditDTO.convertToTable(capitalEntity.getCredito()))
                .setId(capitalEntity.getId())
                .setDescricao(capitalEntity.getDescricao())
                .setValor(capitalEntity.getValor())
                .setEnventDate(capitalEntity.getEnventDate());
        return capital;
    }
}
