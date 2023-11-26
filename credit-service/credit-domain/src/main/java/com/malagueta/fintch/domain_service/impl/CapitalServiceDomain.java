package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CapitalRepository;

public class CapitalServiceDomain {
    private  CapitalRepository capitalRepository;

    public CapitalServiceDomain(CapitalRepository capitalRepository){
        this.capitalRepository=capitalRepository;
    }

    public CapitalEntity addMoney(CreditEntity credito, Double value, String descricao) {
        CapitalEntity curCapital=capitalRepository.findFirstByCreditoOrderByIdDesc(credito);
        CapitalEntity newCapital=CapitalEntity.builder().build();

        if(curCapital!=null){
            newCapital.setValor(curCapital.getValor()+value);
        }else{
            newCapital.setValor(value);
        }

        newCapital.setDescricao(descricao);
        newCapital.setCredito(credito);

        return capitalRepository.save(newCapital);


    }

    public CapitalEntity getLast(CreditEntity credito) {
        return capitalRepository.findFirstByCreditoOrderByIdDesc(credito);
    }
}
