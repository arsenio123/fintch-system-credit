package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.dto.DTOPrestacao;
import com.malagueta.fintch.tables.Pagamento;
import com.malagueta.fintch.tables.Prestacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoJpaRepository extends JpaRepository<Pagamento,Long> {
    public List<Pagamento> findPagamentoByPrestacao( Prestacao prestacao);
}
