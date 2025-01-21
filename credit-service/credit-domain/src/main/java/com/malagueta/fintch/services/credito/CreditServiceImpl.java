package com.malagueta.fintch.services.credito;

//import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.audit.EventSourcing;
import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.services.capital.CapitalServiceDomain;
import com.malagueta.fintch.services.intrest.IntrestServiceDomain;
import com.malagueta.fintch.services.value.CreditoSatus;
import com.malagueta.fintch.entity.*;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.services.value.ErrorCatalog;
import com.malagueta.fintch.port.output.repository.*;
import org.jetbrains.annotations.NotNull;
//import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Author: Arsenio Jeronimo Malagueta
 */


public class CreditServiceImpl extends EventSourcing implements CreditService  {
   // private Logger log= FintechLogg.getLogger(CreditServiceImpl.class);
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

    public CreditEntity creatCredit(@NotNull CreditEntity creditoEntity, EventData eventData) throws ServiceException {
        //log.info("Inico de validacao da criacao do user "+creditoEntity);

        CreditEntity foundCredito=creditRepository.findById(creditoEntity.getId());

        preValidation(creditoEntity, foundCredito);

        ClienteEntity cliente=clienteRepository.findById(creditoEntity.getCliente().getId());
        ProductoEntity producto=productoRepository.findById(creditoEntity.getProducto().getId());
        creditoEntity.setCliente(cliente);
        creditoEntity.setProducto(producto);
        creditoEntity.setCreatDate(LocalDateTime.now());
        creditoEntity.setUpdateDate(LocalDateTime.now());

        loanValidation(creditoEntity);
        if(foundCredito==null){creditoEntity.setEstado(CreditoSatus.PENDENTE);}

        creditoEntity= creditRepository.presiste(creditoEntity);

        //registando o evento de criacao de loan
        eventData.setEventName(CreditEntity.class.getName());
        eventData.setEventInput(creditoEntity.toString());
        eventData.setOperationId(UUID.randomUUID());
        eventData.setEventTime(LocalDateTime.now());
        eventData.setEventOutput(creditoEntity.toString());

        // Implementar rollback
        eventRepository.registeEvent(eventData);


        //Aprovar credito
        if(foundCredito!=null
                && creditoEntity.getEstado().equals(CreditoSatus.VIGOR)
        && !foundCredito.getEstado().equals(CreditoSatus.VIGOR)){

            validatAproval(foundCredito);

            //gera as despesas para creditos aprovados
            CapitalEntity capital=capitalServiceDomain.addMoney(creditoEntity,Double.valueOf(creditoEntity.getValor()),"Valor de desembolso");

            IntrestEntity intrest=intrestServiceDomain.addMoney(creditoEntity,0.0,"juros no desembolso");
            eventRepository.registeEvent(eventData);
        }



            //log.debug("criando o credito "+creditoEntity);





            return creditoEntity;



    }

    private void validatAproval(CreditEntity credit) {
        if(credit.getEstado().equals(CreditoSatus.VIGOR))
            throw new RuntimeException(ErrorCatalog.CREDITO_NO_ESTADO_EM_VIGOR_NAO_PODE_TER_CAMPOS_ALTERADOS.toString());
    }


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

    @Override
    public List<CreditEntity> findCreditoByCriteria(int records, CreditoSatus estado, long clieteID) {
        return creditRepository.findCreditoByCriteria(records,estado,clieteID);
    }

    public void loanValidation(CreditEntity creditoEntity) throws ServiceException {


        if(creditoEntity.getCliente()==null|| creditoEntity.getCliente().getId()==0){
           // log.debug("Erro ao Criar "+ creditoEntity+ "cliente nao tem permisao para ciar um novo cerdito deve fecha os"  );
            throw new ServiceException(ErrorCatalog.CREDITO_CLIENT_MAST_EXIST.toString());
        }

        if(creditoEntity.getValor()<=0){
            throw new ServiceException(ErrorCatalog.CREDITO_VALUE_CANT_BE_LESS_THAN_ZERO.toString());
        }
        if(creditoEntity.getValor()<creditoEntity.getProducto().getCapitalMin()){
            throw new ServiceException(ErrorCatalog.CAPITAL_NAO_PODE_SER_INFERIOS_AO_MIN_PRODUCOT.toString());
        }
        if(creditoEntity.getDoDate()==null||creditoEntity.getBeginDate()==null){
            throw new ServiceException(ErrorCatalog.DEVE_PREENCHER_AS_DATAS.toString());
        }
        if(creditoEntity.getDoDate().compareTo(LocalDate.now())<1){
            throw new ServiceException(ErrorCatalog.DATA_DA_AMORTIZACAO_DEVE_SER_MAIOR_QUE_DATA_ATUAL.toString());
        }
        if(creditoEntity.getDoDate().compareTo(creditoEntity.getBeginDate())<1){
            throw new ServiceException(ErrorCatalog.DATA_DA_AMORTIZACAO_DEVE_SER_MAIOR_QUE_DATA_DE_INICIO.toString());
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

    private void preValidation(CreditEntity newCreditoEntity, CreditEntity creditOldSatage) throws ServiceException {
        if(newCreditoEntity.getProducto()==null|| newCreditoEntity.getProducto().getId()==0){
            throw new ServiceException(ErrorCatalog.CREDITO_PRODUCT_CANT_BE_NULL.toString());
        }
        if(newCreditoEntity.getCliente()==null|| newCreditoEntity.getCliente().getId()==0){
            throw new ServiceException(ErrorCatalog.CREDITO_CLIENT_CANT_BE_NULL.toString());
        }

        if(creditOldSatage!=null){
            if(creditOldSatage.getEstado().equals(CreditoSatus.VIGOR)
                    &&
                    newCreditoEntity.getEstado().equals(CreditoSatus.VIGOR))
            {
                throw new ServiceException(ErrorCatalog.CREDITO_EM_VIGOR_SO_PODE_PASSAR_PARA_VENCIDO.toString());
            }
        }


    }


}
