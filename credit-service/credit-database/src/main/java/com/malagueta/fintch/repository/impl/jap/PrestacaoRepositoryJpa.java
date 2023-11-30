package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.tables.Credito;
import com.malagueta.fintch.tables.Prestacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacaoRepositoryJpa extends JpaRepository<Prestacao, Long> {
    public Prestacao findFirstByCreditoOrderByIdDesc(Credito credito);
}
