package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.entity.PagamentoEntity;

import java.util.List;

public interface PagamentoService {
    PagamentoEntity fazerPagameto(PagamentoEntity pagamento);

    List<PagamentoEntity> getPayBYprestacao(long id_prestacao);
}
