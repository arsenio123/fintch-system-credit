package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.entity.CreditEntity;

import java.util.List;

public interface CreditRepository {
    public CreditEntity storeCredit(CreditEntity credit);

    List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records);
    public CreditEntity presiste(CreditEntity credit);
    public CreditEntity save(CreditEntity credit);

    List<CreditEntity> findOpenCredit(ClienteEntity cliente);

    CreditEntity findById(long id);
}
