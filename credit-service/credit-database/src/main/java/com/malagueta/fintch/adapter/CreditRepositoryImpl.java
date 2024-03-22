package com.malagueta.fintch.adapter;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import com.malagueta.fintch.repository.GenericJDBCRepository;
import com.malagueta.fintch.repository.impl.jdbc.CreditoJDBCRepositoryImpl;
import com.malagueta.fintch.repository.impl.jap.CreditRepositoryJPA;
import com.malagueta.fintch.tables.Credito;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
public class CreditRepositoryImpl extends GenericJDBCRepository<Credito> implements CreditRepository {

    @Autowired
private CreditRepositoryJPA creditRepositoryJPA;

@Autowired
private CreditoJDBCRepositoryImpl creditoJDBCRepository;
    @Override
    public CreditEntity storeCredit(CreditEntity creditEntity) {
        CreditEntity soredEntity= CreditDTO.convertToEntity(creditRepositoryJPA
                .save(CreditDTO.convertToRow(creditEntity)));
        System.out.println("a chamar a implementacao do storege");
        return soredEntity;
    }

    @Override
    public List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records) {
       List<Credito> creditos=  creditoJDBCRepository.findCreditoWithPaginationPrevies(CreditDTO.convertToRow(credito), records);
        return CreditDTO.convertToEntitys(creditos);
    }

    @Transactional
    @Override
    public CreditEntity presiste(CreditEntity creditEntity) {
        log.debug(creditEntity.toString());
        Credito creditRow=CreditDTO.convertToRow(creditEntity);

         creditRow=creditRepositoryJPA.save(creditRow);
        return CreditDTO.convertToEntity(creditRow);
    }

    @Override
    public CreditEntity save(CreditEntity creditEntity) {
        return presiste(creditEntity);
    }

    @Override
    public List<CreditEntity> findOpenCredit(ClienteEntity cliente) {
        List<Credito> credits=creditRepositoryJPA.findAllByCliente_Id(cliente.getId());
//        TODO: este servico deve devolver apenas creditos que estejam em vigor
        return CreditDTO.convertToEntitys(credits);
    }

    @Override
    public CreditEntity findById(long id) {
        return CreditDTO.convertToEntity(creditRepositoryJPA.findById(id).orElse(null));
    }

    @Override
    public List<CreditEntity> listarPorEstadoBeginDateEndDate(CreditoSatus estado, LocalDate minBeginDate, LocalDate maxBeginDate, long valor) {
        return CreditDTO.convertToEntitys(creditoJDBCRepository.listarPorEstadoBeginDateEndDate(estado, minBeginDate, maxBeginDate, valor));
    }

    public List<CreditEntity> findByCreditoWithUpPagination(CreditEntity creditEntity, int records) {
        return CreditDTO.convertToEntitys(
                creditoJDBCRepository.findCreditoWithUpPagination(CreditDTO.convertToRow(creditEntity), records)
        );
    }

    @Override
    public List<CreditEntity> findByDodateLessThan(LocalDate date) {
        return CreditDTO.convertToEntitys(creditRepositoryJPA.findCreditoByDoDateBefore(date));
    }

    @Override
    public List<CreditEntity> findCredtitoByClientID(long clientID) {
        return CreditDTO.convertToEntitys(creditRepositoryJPA.findAllByCliente_Id(clientID));
    }

    @Override
    public List<CreditEntity> findCreditoByCriteria(int records, CreditoSatus estado, long clieteID) {
        return  CreditDTO.convertToEntitys(creditoJDBCRepository.findCreditoByCriteria(records,estado,clieteID));
    }


}
