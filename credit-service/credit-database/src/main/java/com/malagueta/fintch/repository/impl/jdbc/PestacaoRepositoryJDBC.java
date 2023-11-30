package com.malagueta.fintch.repository.impl.jdbc;

import com.malagueta.fintch.domain_service.value.Estado;
import com.malagueta.fintch.port.output.repository.PrestacaoRepository;
import com.malagueta.fintch.repository.GenericJDBCRepository;
import com.malagueta.fintch.tables.Prestacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PestacaoRepositoryJDBC extends GenericJDBCRepository<Prestacao>  {

    public void registarPrestacoes(List<Prestacao> prestacoes) {
        saveAll(prestacoes);
    }


    public Prestacao criarAtulalizar(Prestacao prestacao) {
        return saveUpdate(prestacao);
    }

    public List<Prestacao> findByCreditoStatusDates(Long creditID, Estado status, LocalDate begin, LocalDate end) {
        //pede uma sessao
        //define o objecto que constroi o criteria usando padrao builder
        EntityManager em=getEntityManager();
        CriteriaBuilder builder=em.getCriteriaBuilder();

        CriteriaQuery query= builder.createQuery(Prestacao.class);

        Root<Prestacao> prestacaoRoot=query.from(Prestacao.class);


        List<Predicate> predicates= new ArrayList<>();

        if(creditID!=null){
            Path<Long> idPath=prestacaoRoot.get("credito");
            predicates.add(builder.equal(idPath,creditID));
        }

        if(status!=null){
            Path<Estado> statusPath=prestacaoRoot.get("estado");
            predicates.add(builder.equal(statusPath,status));
        }
        if(begin!=null){
            Path<LocalDate> beginPath=prestacaoRoot.get("vencimento");
            predicates.add(builder.greaterThanOrEqualTo(beginPath,begin));
        }
        if(end!=null){
            Path<LocalDate> endPath=prestacaoRoot.get("vencimento");
            predicates.add(builder.lessThanOrEqualTo(endPath,end));
        }
        query.select(prestacaoRoot).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        List<Prestacao> prestacaos=em.createQuery(query).getResultList();
        em.close();
        return prestacaos;
    }


}
