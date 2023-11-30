package com.malagueta.fintch.adapter;

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
        return CreditDTO.convertToEntity(creditos);
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
        return CreditDTO.convertToEntity(credits);
    }

    @Override
    public CreditEntity findById(long id) {
        return CreditDTO.convertToEntity(creditRepositoryJPA.findById(id).orElse(null));
    }
}
