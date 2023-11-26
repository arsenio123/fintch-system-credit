package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.IntrestEntity;

public interface IntrestRepository {
    IntrestEntity findFirstByCreditoOrderByIdDesc(CreditEntity credito);

    IntrestEntity save(IntrestEntity newIntrest);
}
