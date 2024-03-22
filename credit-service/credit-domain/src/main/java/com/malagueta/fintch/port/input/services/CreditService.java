package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface CreditService {
    public CreditEntity creatCredit(CreditEntity credit, EventData eventData);

    List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records,  @NotNull CreditRepository repository);
    public CreditEntity findCreditoByID(long id);

    List<CreditEntity> listarPorEstadoBeginDateEndDate(CreditoSatus estado, LocalDate minBeginDate, LocalDate maxBeginDate, long valor);

    List<CreditEntity> findByCreditoWithUpPagination(CreditEntity credito, int records);

    CreditEntity updateStatus(Long id, CreditoSatus status);

    List<CreditEntity> findCredtitoByClientID(long clientID);

    List<CreditEntity> findCreditoByCriteria(int records, CreditoSatus estado, long clieteID);
}
