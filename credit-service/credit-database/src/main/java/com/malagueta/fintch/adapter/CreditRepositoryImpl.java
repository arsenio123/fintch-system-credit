package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.CreditDTO;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import com.malagueta.fintch.repository.GenericJDBCRepository;
import com.malagueta.fintch.repository.impl.CreditoJDBCRepositoryImpl;
import com.malagueta.fintch.repository.impl.search.CreditRepositoryJPA;
import com.malagueta.fintch.tables.Credito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditRepositoryImpl extends GenericJDBCRepository<Credito> implements CreditRepository {

    @Autowired
private CreditRepositoryJPA repository;

@Autowired
private CreditoJDBCRepositoryImpl creditoJDBCRepository;
    @Override
    public CreditEntity storeCredit(CreditEntity creditEntity) {
        CreditEntity soredEntity= CreditDTO.convertToEntity(repository
                .save(CreditDTO.convertToTable(creditEntity)));
        System.out.println("a chamar a implementacao do storege");
        return soredEntity;
    }

    @Override
    public List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records) {
       List<Credito> creditos=  creditoJDBCRepository.findCreditoWithPaginationPrevies(CreditDTO.convertToTable(credito), records);
        return CreditDTO.convertToEntity(creditos);
    }

    @Override
    public CreditEntity presiste(CreditEntity creditEntity) {
        Credito creditRow=CreditDTO.convertToTable(creditEntity);

        Credito percistedCredit=creditoJDBCRepository.saveUpdate(creditRow);
        return CreditDTO.convertToEntity(percistedCredit);
    }

    @Override
    public CreditEntity save(CreditEntity credit) {
        return null;
    }

    @Override
    public List<CreditEntity> findOpenCredit(ClienteEntity cliente) {
        return null;
    }
}
