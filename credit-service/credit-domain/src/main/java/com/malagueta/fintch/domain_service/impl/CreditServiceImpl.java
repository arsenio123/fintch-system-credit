package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.value.ErrorCatalog;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CreditServiceImpl implements CreditService {


    @Override
    public CreditEntity creatCredit(@NotNull CreditEntity credit, @NotNull CreditRepository repository) {
        creditoValidation(credit);
        return repository.storeCredit(credit);
    }

    @Override
    public List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records, @NotNull CreditRepository repository) {
        return repository.findByCreditoWithDownPagination(credito, records);
    }

    private void creditoValidation(CreditEntity credit){
        if(credit.getValor()<credit.getProducto().getCapitalMin()){
            throw new RuntimeException(ErrorCatalog.CAPITAL_NAO_PODE_SER_INFERIOS_AO_MIN_PRODUCOT.toString());
        }

    }
}
