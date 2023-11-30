package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.domain_service.value.Estado;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.PrestacaoEntity;

import java.time.LocalDate;
import java.util.List;


public interface PrestacaoRepository {
    public void registarPrestacoes(List<PrestacaoEntity> prestacoeEntities);
    public PrestacaoEntity criarAtulalizar(PrestacaoEntity prestacao);

    List<PrestacaoEntity> findByCreditoStatusDates(Long creditID, Estado status, LocalDate begin, LocalDate end);

    PrestacaoEntity findByID(long id);

    PrestacaoEntity findFirstByCreditoOrderByIdDesc(CreditEntity creditEntity);

    PrestacaoEntity getById(long id);
}
