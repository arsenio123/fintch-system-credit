package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.dto.DTOCapital;
import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CapitalRepository;
import com.malagueta.fintch.repository.impl.jap.CapitalRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j(topic = "CapitalRepositoryImpl")
@Repository
public class CapitalRepositoryImpl implements CapitalRepository {

    private CapitalRepositoryJpa capitalRepositoryJpa;

    CapitalRepositoryImpl(CapitalRepositoryJpa capitalRepositoryJpa){
        this.capitalRepositoryJpa=capitalRepositoryJpa;
    }
     @Override
    public CapitalEntity findFirstByCreditoOrderByIdDesc(CreditEntity creditEntity) {
        return DTOCapital
                .convertToEntity(capitalRepositoryJpa
                .findFirstByCreditoOrderByIdDesc(CreditDTO.convertToRow(creditEntity)));
    }

    @Transactional
    @Override
    public CapitalEntity save(CapitalEntity capitalEntity) {
        log.debug("precisting Capital: "+capitalEntity);

        return DTOCapital
                .convertToEntity(capitalRepositoryJpa.save(
                        DTOCapital.convertToCapital(capitalEntity))
                );
    }
}
