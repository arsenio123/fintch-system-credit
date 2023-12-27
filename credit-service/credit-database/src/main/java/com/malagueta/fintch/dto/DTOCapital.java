package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.tables.Capital;
import org.jetbrains.annotations.NotNull;

public class DTOCapital {
    public static CapitalEntity convertToEntity(Capital capital) {
        if(capital==null){
            return null;
        }
        CapitalEntity capitalEntity=CapitalEntity.builder()
                .id(capital.getId())
                .credito(CreditDTO.convertToEntity(capital.getCredito()))
                .descricao(capital.getDescricao())
                .valor(capital.getValor())
                .enventDate(capital.getEnventDate())
                .build();
        return capitalEntity;
    }

    public static Capital convertToRow( CapitalEntity capitalEntity){
        if(capitalEntity==null)
            return null;
        Capital capital=new Capital();
        capital.setCredito(CreditDTO.convertToRow(capitalEntity.getCredito()))
                .setId(capitalEntity.getId())
                .setDescricao(capitalEntity.getDescricao())
                .setValor(capitalEntity.getValor())
                .setEnventDate(capitalEntity.getEnventDate());
        return capital;
    }
}
