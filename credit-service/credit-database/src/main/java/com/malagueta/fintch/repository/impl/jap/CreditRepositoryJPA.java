package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.tables.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditRepositoryJPA extends JpaRepository<Credito,Long> {
    public List<Credito> findAllByCliente_Id(long id);

    public List<Credito> findCreditoByDoDateBefore(LocalDate doDate);



}
