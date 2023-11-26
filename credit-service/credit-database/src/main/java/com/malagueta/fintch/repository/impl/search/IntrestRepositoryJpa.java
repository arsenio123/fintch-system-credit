package com.malagueta.fintch.repository.impl.search;

import com.malagueta.fintch.tables.Credito;
import com.malagueta.fintch.tables.Intrest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntrestRepositoryJpa extends JpaRepository<Intrest,Long> {
    public Intrest findFirstByCreditoOrderByIdDesc(Credito credito);
}
