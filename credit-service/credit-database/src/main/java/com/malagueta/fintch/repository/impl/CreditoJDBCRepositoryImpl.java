package com.malagueta.fintch.repository.impl;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.repository.CreditoRepository;
import com.malagueta.fintch.repository.GenericJDBCRepository;
import com.malagueta.fintch.repository.impl.search.SearchCredito;
import com.malagueta.fintch.tables.Cliente;
import com.malagueta.fintch.tables.Credito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Profile("JPA")
@Component
public class CreditoJDBCRepositoryImpl
        extends GenericJDBCRepository<Credito>
        implements CreditoRepository {
    Logger log= LoggerFactory.getLogger(CreditoJDBCRepositoryImpl.class);

    public Credito getAllCredit(Credito credito) {
        return findByID(credito);
    }
    @Override
    public List<Credito> getAllCredit() {
        return getAll(new Credito());
    }

    @Autowired
    private SearchCredito searchCredito;

    @Override
    public Credito createCredito(Credito credito) {
        return  saveUpdate(credito);
    }

    @Override
    public List<Credito> findOpenCredit(Cliente cliente) {
        EntityManager storage=getEntityManager();

        List<Credito> credits= storage.createQuery("select c from Credito c where c.cliente=:client_id and c.estado= :vigor", Credito.class)
                .setParameter("client_id",cliente)
                .setParameter("vigor", CreditoSatus.VIGOR)
                .getResultList();
        return credits;
    }

    @Override
    public Credito atulizaCredito(Credito credito) {
        return  saveUpdate(credito);
    }

    @Override
    public List<Credito> findCreditoWithPagination(Credito credito, int records) {
        log.debug("inicio do findCreditoWithPagination, input "+credito+", recordes "+records);
        EntityManager em=getEntityManager();

        CriteriaBuilder crBuilder=em.getCriteriaBuilder();
        CriteriaQuery query=crBuilder.createQuery(Credito.class);
        Root rootCredito=query.from(Credito.class);

        //build predicate

        List<Predicate> predicates=new ArrayList<>();

        if(credito!=null){


            if(credito.getEstado()!=null){
                log.debug("incluindo o estado="+credito.getEstado());
                Path<Long> estadoPath=rootCredito.get("estado");
                predicates.add(crBuilder.equal(estadoPath,credito.getEstado()));
            }
            //last index
            if(credito.getId()!=null){
                if(credito.getId()<=0){
                    System.out.println("o ultimo index e: "+searchCredito.findFirstByOrderByIdDesc());

                    credito.setId(searchCredito.findFirstByOrderByIdDesc().getId());}

                log.debug("incluido ID="+credito.getId());
                Path<Long> idPath=rootCredito.get("id");
                predicates.add(crBuilder.lessThan(idPath,credito.getId()));
            }

        }
        query.select(rootCredito)
                .where(crBuilder
                        .and(predicates.toArray(new Predicate[predicates.size()]))
                ).orderBy(crBuilder.desc(rootCredito.get("id"))
                );

        //executa a query na base de dados
        List<Credito> creditos=em.createQuery(query).setMaxResults(records).getResultList();
        log.debug("findCreditoWithPagination " +creditos.toString());

        return creditos;
    }

    @Override
    public List<Credito> findCreditoWithPaginationPrevies(Credito credito, int records) {
        log.debug("inicio do findCreditoWithPagination, input "+credito+", recordes "+records);
        EntityManager em=getEntityManager();

        CriteriaBuilder crBuilder=em.getCriteriaBuilder();
        CriteriaQuery query=crBuilder.createQuery(Credito.class);
        Root rootCredito=query.from(Credito.class);

        //build predicate

        List<Predicate> predicates=new ArrayList<>();

        if(credito!=null){


            if(credito.getEstado()!=null&& !credito.getEstado().equals("")){
                log.debug("incluindo o estado="+credito.getEstado());
                Path<Long> estadoPath=rootCredito.get("estado");
                predicates.add(crBuilder.equal(estadoPath,credito.getEstado()));
            }
            //last index
            if(credito.getId()!=null){
                if(credito.getId()<=0){
                    System.out.println("o ultimo index e: "+searchCredito.findFirstByOrderByIdAsc());

                    credito.setId(searchCredito.findFirstByOrderByIdAsc().getId());}

                log.debug("incluido ID="+credito.getId());
                Path<Long> idPath=rootCredito.get("id");
                predicates.add(crBuilder.lessThan(idPath,credito.getId()));
            }

        }
        query.select(rootCredito)
                .where(crBuilder
                        .and(predicates.toArray(new Predicate[predicates.size()]))
                ).orderBy(crBuilder.desc(rootCredito.get("id"))
                );

        //executa a query na base de dados
        List<Credito> creditos=em.createQuery(query).setMaxResults(records).getResultList();
        log.debug("findCreditoWithPagination " +creditos.toString());

        return creditos;
    }


    /**
     *  EntityManager manager=emgFactory.createEntityManager();
     *         System.out.println("numero de entraas "+manager.createQuery("select c from Credito c").getResultList().size());
     *         List<Credito> creditos= manager.createQuery("select c from Credito c").getResultList();
     *         manager.close();
     */
}
