package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.CreditEntity;

import java.util.List;

public interface CreditRepository {
    public CreditEntity storeCredit(CreditEntity credit);

    List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records);
}
