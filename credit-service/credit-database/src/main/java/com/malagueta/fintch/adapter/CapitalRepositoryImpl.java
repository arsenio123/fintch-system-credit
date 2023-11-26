package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.dto.DTOCapital;
import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CapitalRepository;
import com.malagueta.fintch.repository.impl.search.CapitalRepositoryJpa;
import org.springframework.stereotype.Repository;


@Repository
public class CapitalRepositoryImpl implements CapitalRepository {

    private CapitalRepositoryJpa capitalRepositoryJpa;

    CapitalRepositoryImpl(CapitalRepositoryJpa capitalRepositoryJpa){
        this.capitalRepositoryJpa=capitalRepositoryJpa;
    }
     @Override
    public CapitalEntity findFirstByCreditoOrderByIdDesc(CreditEntity credito) {
        return DTOCapital.convertToEntity(capitalRepositoryJpa.findFirstByCreditoOrderByIdDesc(CreditDTO.convertToTable(credito)));
    }

    @Override
    public CapitalEntity save(CapitalEntity capitalEntity) {
        return DTOCapital
                .convertToEntity(capitalRepositoryJpa.save(
                        DTOCapital.convertToCapital(capitalEntity))
                );
    }
}
