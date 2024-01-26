package com.malagueta.fintch.api;

import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.*;
import com.malagueta.fintch.domain_service.impl.factory.CreditServiceFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CreditoAPI {

    Logger log= FintechLogg.getLogger(CreditoAPI.class);
    //Deve ser colocado on fly

    private  CreditRepository creditRepository;
    private CreditService creditoService;
    private CapitalRepository capitalRepository;
    private IntrestRepository intrestRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;

    private EventRepository eventRepository;
    private AppConfig config;


    public CreditoAPI(CreditRepository creditRepository,
                      CapitalRepository capitalRepository,
                      IntrestRepository intrestRepository,
                      ClienteRepository clienteRepository,
                      ProductoRepository productoRepository,
                      EventRepository eventRepository,
                      AppConfig config){
        this.config=config;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.creditRepository=creditRepository;
        this.productoRepository=productoRepository;
        this.eventRepository= eventRepository;

        this.creditoService=CreditServiceFactory.getCreditService(config.getClientServiceImpl(),
                creditRepository,
                capitalRepository,
                intrestRepository,
                clienteRepository,
                productoRepository,
                eventRepository);
    }

    @CrossOrigin
    @PostMapping("credito/creat")
    public CreditEntity createCredito(@RequestBody CreditEntity creditEntity,
                                      @RequestHeader (name = "Authorization") String sessionID){

        log.debug("creating credit from input: "+creditEntity.toString());
         EventData eventData= EventData.builder().build();
         eventData.setEventInput(creditEntity.toString());
         eventData.setSessionId(sessionID);
         eventData.setOperationId(UUID.randomUUID());
         creditEntity= creditoService.creatCredit(creditEntity, eventData);
        return creditEntity;
    }
    @PostMapping("credito/atualiza")
    @CrossOrigin
    public CreditEntity atualizarCredito(@RequestBody CreditEntity creditEntity, @RequestHeader (name = "Authorization") String sessionID){
        log.debug("creating credit from input: "+creditEntity.toString());
        EventData eventData= EventData.builder().sessionId(sessionID).build();

        return creditEntity= creditoService.creatCredit(creditEntity,eventData);
    }


    @GetMapping ("credito/list")
    public List<CreditEntity> listCredito(){
        return null;
    }
    @GetMapping("credito/list/critirea")
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROLE_CREDITOS')")
    public List<CreditEntity> getbyCritirea(@RequestParam(name="estado" ,required = false) CreditoSatus estado,
                                       @RequestParam(name="minBeginDate", required = false) LocalDate minBeginDate,
                                       @RequestParam(name="maxBeginDate", required = false) LocalDate maxBeginDate,
                                       @RequestParam(name="valor", required = false) long valor) {
        return creditoService.listarPorEstadoBeginDateEndDate(estado,minBeginDate,maxBeginDate,valor);

    }

    @PostMapping("credito/alterar/estado")
    @CrossOrigin
    public CreditEntity atualizar(Long id, CreditoSatus status){
        log.debug("actulaizando Credito com o ID:"+id);
        return creditoService.updateStatus(id, status);
    }
    @GetMapping("credito/list/id")
    @CrossOrigin
    public CreditEntity findByID(@RequestParam Long id){
        log.debug("Procurando Credito com o ID:"+id);
        return creditoService.findCreditoByID(id);
    }

    @GetMapping("credito/list/critirea/v2")
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROLE_CREDITOS')")
    public List<CreditEntity> findByCreditoWithUpPagination(
            @RequestParam(name="id" ,required = false) long id
            ,@RequestParam(name="records", required = false) int records
            ,@RequestParam(name="estado", required = false) CreditoSatus estado,
            @RequestHeader("Authorization") String token
    ) {

        System.err.println("header Authorization"+token);
        try{
            CreditEntity credito=CreditEntity.builder().id(id).estado(estado).build();

            return creditoService.findByCreditoWithUpPagination(credito,records) ;
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }


    }
  /*  */

    @GetMapping("credito/list/findCreditoByClientID")
    @CrossOrigin
    public List<CreditEntity> findCreditoByClientID(@RequestParam(name = "clientID") long clientID, @RequestHeader("Authorization") String token){
        List<CreditEntity> output=new ArrayList<CreditEntity>();
        output=creditoService.findCredtitoByClientID(clientID);
        return output;
    }

    @GetMapping("credito/list/critirea/previes")
    @CrossOrigin
    public List<CreditEntity> findByCreditoWithPaginationPrevies(
            @RequestParam(name="id" ,required = false) Long id
            ,@RequestParam(name="records", required = false) int records
            ,@RequestParam(name="estado", required = false) CreditoSatus estado
            ,@RequestParam(name="initDate", required = false) LocalDateTime initDate
    ) {
        try{
            CreditEntity credito= CreditEntity.builder()
                    .id(id)
                    .estado(estado)
                    .creatDate(initDate)
                    .build();

            return creditoService.findByCreditoWithDownPagination(credito,records, creditRepository) ;
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }


    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String errorHandler(RuntimeException ex){
        return ex.getMessage();
    }

}
