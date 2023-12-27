package com.malagueta.fintch.adapter;

import com.malagueta.fintch.domain_service.value.Estado;
import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.dto.DTOPrestacao;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.port.output.repository.PrestacaoRepository;
import com.malagueta.fintch.repository.impl.jap.PrestacaoRepositoryJpa;
import com.malagueta.fintch.repository.impl.jdbc.PestacaoRepositoryJDBC;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PrestacaoRepositoryImpl implements PrestacaoRepository {

    private PrestacaoRepositoryJpa prestacaoRepositoryJpa;
    private PestacaoRepositoryJDBC pestacaoRepositoryJDBC;

    PrestacaoRepositoryImpl(PrestacaoRepositoryJpa prestacaoRepositoryJpa,
                            PestacaoRepositoryJDBC pestacaoRepositoryJDBC){
        this.prestacaoRepositoryJpa=prestacaoRepositoryJpa;
        this.pestacaoRepositoryJDBC=pestacaoRepositoryJDBC;
    }

    @Override
    public void registarPrestacoes(List<PrestacaoEntity> prestacoeEntities) {

    }

    @Override
    public PrestacaoEntity criarAtulalizar(PrestacaoEntity prestacaoEntity) {
        return DTOPrestacao.convetToEntity(prestacaoRepositoryJpa.save(DTOPrestacao.convertToRow(prestacaoEntity)));
    }

    @Override
    public List<PrestacaoEntity> findByCreditoStatusDates(Long creditID, Estado status, LocalDate begin, LocalDate end) {
        return DTOPrestacao.convetToEntity(pestacaoRepositoryJDBC.findByCreditoStatusDates(creditID,status,begin,end));
    }

    @Override
    public PrestacaoEntity findByID(long id) {
        return DTOPrestacao.convetToEntity(prestacaoRepositoryJpa.findById(id).orElse(null));
    }

    @Override
    public PrestacaoEntity findFirstByCreditoOrderByIdDesc(CreditEntity creditEntity) {
        return DTOPrestacao.convetToEntity(prestacaoRepositoryJpa.findFirstByCreditoOrderByIdDesc(CreditDTO.convertToRow(creditEntity)));
    }

    @Override
    public PrestacaoEntity getById(long id) {
        return DTOPrestacao.convetToEntity(prestacaoRepositoryJpa.findById(id).orElse(null)) ;
    }
}
