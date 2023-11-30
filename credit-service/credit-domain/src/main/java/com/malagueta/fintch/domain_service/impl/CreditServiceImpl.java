package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.entity.*;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.value.ErrorCatalog;
import com.malagueta.fintch.port.output.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Arsenio Jeronimo Malagueta
 */

//@Slf4j(topic = "CreditServiceImpl")

public class CreditServiceImpl implements CreditService {
    private CapitalServiceDomain capitalServiceDomain;

    private IntrestServiceDomain intrestServiceDomain;

    private CreditRepository creditRepository;
    private CapitalRepository capitalRepository;
    private IntrestRepository intrestRepository;

    private ClienteRepository clienteRepository;

    private ProductoRepository productoRepository;

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
                             ProductoRepository productoRepository){
        this.creditRepository=creditRepository;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.clienteRepository=clienteRepository;
        this.productoRepository=productoRepository;

        this.intrestServiceDomain=new IntrestServiceDomain(intrestRepository);
        this.capitalServiceDomain=new CapitalServiceDomain(capitalRepository);

    }
    public CreditServiceImpl(@NotNull CreditRepository creditRepository){
        this.creditRepository=creditRepository;
    }

    @Override
    public CreditEntity creatCredit(@NotNull CreditEntity creditoEntity) {
        //log.info("Inico de validacao da criacao do user "+creditoEntity);

        preValidation(creditoEntity);
        ClienteEntity cliente=clienteRepository.findById(creditoEntity.getCliente().getId());
        ProductoEntity producto=productoRepository.findById(creditoEntity.getProducto().getId());
        creditoEntity.setCliente(cliente);
        creditoEntity.setProducto(producto);
        creditoEntity.setCreatDate(LocalDateTime.now());
        creditoEntity.setUpdateDate(LocalDateTime.now());


        postvalidation(creditoEntity);

        List<CreditEntity> openCredits =creditRepository.findOpenCredit(creditoEntity.getCliente());

        if(openCredits!=null && openCredits.size()<Integer.valueOf(10))//(env.getProperty("credit.max.limit")) //TODO o numero tem que vir por parametro e deve ser definida por negocio
         {
            //log.debug("criando o credito "+creditoEntity);
            creditoEntity= creditRepository.presiste(creditoEntity);
            CapitalEntity capital=capitalServiceDomain.addMoney(creditoEntity,Double.valueOf(creditoEntity.getValor()),"Valor de desembolso");

            IntrestEntity intrest=intrestServiceDomain.addMoney(creditoEntity,0.0,"juros no desembolso");

            return creditoEntity;
        }else {
           // log.debug("cliente nao tem permisao para ciar um novo cerdito deve fecha os"+openCredits.size() +" creditos abertos"  );
        }
        return null;
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

    public void postvalidation(CreditEntity creditoEntity)  {


        if(creditoEntity.getCliente()==null|| creditoEntity.getCliente().getId()==0){
            throw new RuntimeException(ErrorCatalog.CREDITO_CLIENT_MAST_EXIST.toString());
        }

        if(creditoEntity.getValor()<=0){
            throw new RuntimeException(ErrorCatalog.CREDITO_VALUE_CANT_BE_LESS_THAN_ZERO.toString());
        }
        if(creditoEntity.getValor()<creditoEntity.getProducto().getCapitalMin()){
            throw new RuntimeException(ErrorCatalog.CAPITAL_NAO_PODE_SER_INFERIOS_AO_MIN_PRODUCOT.toString());
        }

        //entra para este me
        /*try {
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
