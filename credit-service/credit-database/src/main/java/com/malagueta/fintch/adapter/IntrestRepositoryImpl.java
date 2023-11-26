package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.dto.DTOIntrest;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.port.output.repository.IntrestRepository;
import com.malagueta.fintch.repository.impl.search.IntrestRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class IntrestRepositoryImpl implements IntrestRepository {

    private IntrestRepositoryJpa intrestRepositoryJpa;

    public IntrestRepositoryImpl(IntrestRepositoryJpa intrestRepositoryJpa){
        this.intrestRepositoryJpa=intrestRepositoryJpa;
    }

    @Override
    public IntrestEntity findFirstByCreditoOrderByIdDesc(CreditEntity creditoEntity) {
        return DTOIntrest.convertToEntity(
                intrestRepositoryJpa
                .findFirstByCreditoOrderByIdDesc(CreditDTO
                        .convertToTable(creditoEntity)
                ));
    }

    @Override
    public IntrestEntity save(IntrestEntity newIntrest) {
        return null;
    }
}
