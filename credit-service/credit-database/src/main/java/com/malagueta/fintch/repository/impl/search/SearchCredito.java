package com.malagueta.fintch.repository.impl.search;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.tables.Cliente;
import com.malagueta.fintch.tables.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SearchCredito extends JpaRepository<Credito, Long>, QueryByExampleExecutor<Credito> {


    @Query("select c from Credito c where c.cliente=:cliente or c.estado=:estado")

    public List<Credito> findByClienteAndEstado(Cliente cliente, CreditoSatus estado);



    @Query("select c from Credito c where c.estado=?1")
    public List<Credito> findByEstado(CreditoSatus estado);//, Date beginDateMin,Date beginDateMax);

    @Query("select c from Credito c where :estado is null or c.estado=:estado and " +
            ":beginDateMin is null or c.beginDate>=:beginDateMin and" +
            " :beginDateMax is null or c.beginDate<=:beginDateMax and " +

            ":valor is null or c.valor=:valor")
    public List<Credito> findByEstadoAndBegindate(Optional <CreditoSatus> estado,
                                                  Optional <Date> beginDateMin,
                                                  Optional <Date> beginDateMax,
                                                  Optional <Long> valor);

    @Query("select c from Credito c where c.estado=?1 and  c.doDate>=?2 and c.beginDate<=?2")
    public List<Credito> findByStatusAndDoDate(CreditoSatus estado,
                                               Date endDateMax);

    @Query("select c from Credito c where c.id=:id")
    public Credito findCreditoById(long id);

    public Credito findFirstByOrderByIdDesc();

    @Query("select c from Credito c where c.id<:crId order by c.id DESC")
    public List<Credito> findFirst10(Long crId);

    public Credito findFirstByOrderByIdAsc();


    //findOne

 }
