package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;

public interface CapitalRepository {

    CapitalEntity findFirstByCreditoOrderByIdDesc(CreditEntity credito);

    CapitalEntity save(CapitalEntity newCapital);
}
