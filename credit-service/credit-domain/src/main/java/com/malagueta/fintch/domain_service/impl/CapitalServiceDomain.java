package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CapitalRepository;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Log
public class CapitalServiceDomain {
    private  CapitalRepository capitalRepository;

    public CapitalServiceDomain(CapitalRepository capitalRepository){
        this.capitalRepository=capitalRepository;
    }

    public CapitalEntity addMoney(CreditEntity creditoEntity, Double value, String descricao) {
        log.info("adding money "+value +" to "+creditoEntity+ " decription:"+descricao);

        CapitalEntity curCapital=capitalRepository.findFirstByCreditoOrderByIdDesc(creditoEntity);
        CapitalEntity newCapital=CapitalEntity.builder().
                credito(creditoEntity)
                .enventDate(LocalDateTime.now())
                .build();

        if(curCapital!=null){

            newCapital.setValor(curCapital.getValor()+value);
        }else{
            newCapital.setValor(value);
        }

        newCapital.setDescricao(descricao);
        newCapital.setCredito(creditoEntity);
        //event.add(operation, dados, )
        return capitalRepository.save(newCapital);



    }

    public CapitalEntity getLast(CreditEntity credito) {
        return capitalRepository.findFirstByCreditoOrderByIdDesc(credito);
    }
}
