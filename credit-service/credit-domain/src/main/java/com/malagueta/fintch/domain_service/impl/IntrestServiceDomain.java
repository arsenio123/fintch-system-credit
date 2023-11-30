package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.port.output.repository.IntrestRepository;

import java.time.LocalDateTime;

public class IntrestServiceDomain {
    private IntrestRepository repository;
    public IntrestServiceDomain(IntrestRepository repository){
        this.repository=repository;
    }


    public IntrestEntity addMoney(CreditEntity credito, Double money, String description){
        IntrestEntity lastIntrest=repository.findFirstByCreditoOrderByIdDesc(credito);
        IntrestEntity newIntrest;
        if(lastIntrest==null){
            newIntrest=IntrestEntity.builder()
                    .valor(Double.valueOf(money))
                    .credito(credito)
                    .descricao(description)
                    .enventDate(LocalDateTime.now()).build();
        }else{
            newIntrest=IntrestEntity.builder()
                    .valor(lastIntrest.getValor()+money)
                    .credito(lastIntrest.getCredito())
                    .descricao(description)
                    .enventDate(LocalDateTime.now()).build();
        }
        return repository.save(newIntrest);
    }

    public IntrestEntity getLast(Long credito_id) {
        CreditEntity credito= CreditEntity.builder().id(credito_id).build();
        return repository.findFirstByCreditoOrderByIdDesc(credito);
    }
}
