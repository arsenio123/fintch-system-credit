package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.PagamentoEntity;

import java.util.List;

public interface PagamentoRepository {

    public PagamentoEntity fazerPagameto(PagamentoEntity pagamento);

    public List<PagamentoEntity> getPayBYprestacao(long id_prestacao);
}
