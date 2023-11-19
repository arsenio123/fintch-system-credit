package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CreditService {
    public CreditEntity creatCredit(CreditEntity credit, @NotNull CreditRepository repository);

    List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records,  @NotNull CreditRepository repository);
}
