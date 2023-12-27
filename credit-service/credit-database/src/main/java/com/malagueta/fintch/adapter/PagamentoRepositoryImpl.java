package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.DTOPagamento;
import com.malagueta.fintch.entity.PagamentoEntity;
import com.malagueta.fintch.port.output.repository.PagamentoRepository;
import com.malagueta.fintch.repository.impl.jap.PagamentoJpaRepository;
import com.malagueta.fintch.tables.Prestacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagamentoRepositoryImpl implements PagamentoRepository {
    PagamentoJpaRepository pagamentoJpaRepository;

    PagamentoRepositoryImpl(PagamentoJpaRepository pagamentoJpaRepository){
        this.pagamentoJpaRepository=pagamentoJpaRepository;
    }
    @Override
    public PagamentoEntity fazerPagameto(PagamentoEntity pagamentoEntity) {
        return DTOPagamento.convertToEntity(
        pagamentoJpaRepository.save(
                        DTOPagamento.convertToRow(pagamentoEntity))
        );
    }

    @Override
    public List<PagamentoEntity> getPayBYprestacao(long id_prestacao) {
        Prestacao prestacao=new Prestacao();
        prestacao.setId(id_prestacao);
        return DTOPagamento.convertToEntitys(pagamentoJpaRepository.findPagamentoByPrestacao(prestacao));
    }
}
