package com.malagueta.fintch.repository.impl.search;

import com.malagueta.fintch.tables.Capital;
import com.malagueta.fintch.tables.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CapitalRepositoryJpa extends JpaRepository<Capital, Long > {
    public Capital findFirstByCreditoOrderByIdDesc(Credito convertToTable);
}
