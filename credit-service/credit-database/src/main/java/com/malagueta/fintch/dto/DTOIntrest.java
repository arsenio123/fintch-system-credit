package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.tables.Intrest;

public class DTOIntrest {
    public static Intrest convertToRow( IntrestEntity intrestEntity) {
        if(intrestEntity==null)
            return null;
        Intrest intrestRow= new Intrest().setId(intrestEntity.getId())
                .setCredito(CreditDTO.convertToRow(intrestEntity.getCredito()))
                .setValor(intrestEntity.getValor())
                .setEnventDate(intrestEntity.getEnventDate());
        return intrestRow;

    }

    public static IntrestEntity convertToEntity( Intrest intrestRow) {
        if(intrestRow==null)
            return null;
        return IntrestEntity.builder()
                .credito(CreditDTO.convertToEntity(intrestRow.getCredito()))
                .id(intrestRow.getId())
                .enventDate(intrestRow.getEnventDate())
                .descricao(intrestRow.getDescricao())
                .valor(intrestRow.getValor()).build();
    }
}
