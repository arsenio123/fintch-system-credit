package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.domain_service.impl.CapitalServiceDomain;
import com.malagueta.fintch.domain_service.impl.IntrestServiceDomain;
import com.malagueta.fintch.domain_service.value.Estado;
import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.port.output.repository.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface PrestacaoService {
    List<PrestacaoEntity> listar(Long creditID,
                                 Estado status,
                                 LocalDate begin,
                                 LocalDate end,
                                 PrestacaoRepository prestacaoRepository);

    PrestacaoEntity create(PrestacaoEntity prestacaoEntity,
                           @NotNull CapitalRepository capitalServiceDomain,
                           @NotNull IntrestRepository intrestServiceDomain,
                           @NotNull PrestacaoRepository prestacaoRepository,
                           @NotNull CreditRepository creditRepository,
                           @NotNull ProductoRepository productoRepository);

    PrestacaoEntity listarPorID(long id,@NotNull PrestacaoRepository prestacaoRepository);
}
