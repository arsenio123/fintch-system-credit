package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.audit.EventSourcing;
import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.entity.*;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.value.ErrorCatalog;
import com.malagueta.fintch.port.output.repository.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Author: Arsenio Jeronimo Malagueta
 */


public class CreditServiceImpl extends EventSourcing implements CreditService  {
    private Logger log= FintechLogg.getLogger(CreditServiceImpl.class);
    private CapitalServiceDomain capitalServiceDomain;

    private IntrestServiceDomain intrestServiceDomain;

    private CreditRepository creditRepository;
    private CapitalRepository capitalRepository;
    private IntrestRepository intrestRepository;

    private ClienteRepository clienteRepository;

    private ProductoRepository productoRepository;

    private EventRepository eventRepository;

    /**
     *
     * @param creditRepository
     * @param capitalRepository
     * @param intrestRepository
     * @param clienteRepository
     * @param productoRepository
     */
    public CreditServiceImpl(@NotNull CreditRepository creditRepository,
                             @NotNull CapitalRepository capitalRepository,
                             @NotNull IntrestRepository intrestRepository,
                             ClienteRepository clienteRepository,
                             ProductoRepository productoRepository,
                             EventRepository eventRepository){
        this.creditRepository=creditRepository;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.clienteRepository=clienteRepository;
        this.productoRepository=productoRepository;
        this.eventRepository=eventRepository;

        this.intrestServiceDomain=new IntrestServiceDomain(intrestRepository);
        this.capitalServiceDomain=new CapitalServiceDomain(capitalRepository);


    }
    public CreditServiceImpl(@NotNull CreditRepository creditRepository){
        this.creditRepository=creditRepository;
    }

    @Override

    public CreditEntity creatCredit(@NotNull CreditEntity creditoEntity, EventData eventData) {
        log.info("Inico de validacao da criacao do user "+creditoEntity);

        preValidation(creditoEntity);
        ClienteEntity cliente=clienteRepository.findById(creditoEntity.getCliente().getId());
        ProductoEntity producto=productoRepository.findById(creditoEntity.getProducto().getId());
        creditoEntity.setCliente(cliente);
        creditoEntity.setProducto(producto);
        creditoEntity.setCreatDate(LocalDateTime.now());
        creditoEntity.setUpdateDate(LocalDateTime.now());


        loanValidation(creditoEntity);


            log.debug("criando o credito "+creditoEntity);
            creditoEntity= creditRepository.presiste(creditoEntity);

            //registando o evento de criacao de loan
            eventData.setEventName(CreditEntity.class.getName());
            eventData.setEventInput(creditoEntity.toString());
            eventData.setOperationId(UUID.randomUUID());
            eventData.setEventTime(LocalDateTime.now());
            eventData.setEventOutput(creditoEntity.toString());

            // Implementar rollback
            eventRepository.registeEvent(eventData);

            CapitalEntity capital=capitalServiceDomain.addMoney(creditoEntity,Double.valueOf(creditoEntity.getValor()),"Valor de desembolso");

            IntrestEntity intrest=intrestServiceDomain.addMoney(creditoEntity,0.0,"juros no desembolso");
            eventRepository.registeEvent(eventData);
            return creditoEntity;



    }
/*
    public void creatCreditRollBAck(){

    }

*/


    @Override
    public List<CreditEntity> findByCreditoWithDownPagination(CreditEntity credito, int records, @NotNull CreditRepository creditRepository) {
        return creditRepository.findByCreditoWithDownPagination(credito, records);
    }

    @Override
    public CreditEntity findCreditoByID(long id) {
        return creditRepository.findById(id);
    }

    @Override
    public List<CreditEntity> listarPorEstadoBeginDateEndDate(CreditoSatus estado, LocalDate minBeginDate, LocalDate maxBeginDate, long valor) {
        return creditRepository.listarPorEstadoBeginDateEndDate(estado, minBeginDate,maxBeginDate, valor);
    }

    @Override
    public List<CreditEntity> findByCreditoWithUpPagination(CreditEntity creditoEntity, int records) {
        return creditRepository.findByCreditoWithUpPagination(creditoEntity,records);
    }

    @Override
    public CreditEntity updateStatus(Long id, CreditoSatus status) {
        return null;
    }

    @Override
    public List<CreditEntity> findCredtitoByClientID(long clientID) {
        return creditRepository.findCredtitoByClientID(clientID);
    }

    public void loanValidation(CreditEntity creditoEntity)  {


        if(creditoEntity.getCliente()==null|| creditoEntity.getCliente().getId()==0){
            log.debug("Erro ao Criar "+ creditoEntity+ "cliente nao tem permisao para ciar um novo cerdito deve fecha os"  );
            throw new RuntimeException(ErrorCatalog.CREDITO_CLIENT_MAST_EXIST.toString());
        }

        if(creditoEntity.getValor()<=0){
            throw new RuntimeException(ErrorCatalog.CREDITO_VALUE_CANT_BE_LESS_THAN_ZERO.toString());
        }
        if(creditoEntity.getValor()<creditoEntity.getProducto().getCapitalMin()){
            throw new RuntimeException(ErrorCatalog.CAPITAL_NAO_PODE_SER_INFERIOS_AO_MIN_PRODUCOT.toString());
        }
        List<CreditEntity> openCredits =creditRepository.findOpenCredit(creditoEntity.getCliente());

        /*if(openCredits!=null && openCredits.size()<Integer.valueOf(env.getProperty("credit.max.limit")) //TODO licenca deve vir do Tenence pela base de dados
        {

        }*/
       /* try {
            Licensa licensa=new Licensa();
            if(!licensa.valideRecordes(creditoRepository.getAllCredit().size())){
                throw new RuntimeException(RuntimeErrorCatalog.LICENSA_EXPIRADA.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    private void preValidation(CreditEntity creditoEntity) {
        if(creditoEntity.getProducto()==null|| creditoEntity.getProducto().getId()==0){
            throw new RuntimeException(ErrorCatalog.CREDITO_PRODUCT_CANT_BE_NULL.toString());
        }
        if(creditoEntity.getCliente()==null|| creditoEntity.getCliente().getId()==0){
            throw new RuntimeException(ErrorCatalog.CREDITO_CLIENT_CANT_BE_NULL.toString());
        }
    }


}
