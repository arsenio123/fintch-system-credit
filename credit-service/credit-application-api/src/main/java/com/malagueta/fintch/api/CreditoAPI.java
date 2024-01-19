package com.malagueta.fintch.api;

import com.malagueta.fintch.FintechLogg;
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
import java.util.List;

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

    private AppConfig config;


    public CreditoAPI(CreditRepository creditRepository,
                      CapitalRepository capitalRepository,
                      IntrestRepository intrestRepository,
                      ClienteRepository clienteRepository,
                      ProductoRepository productoRepository,
                      AppConfig config){
        this.config=config;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.creditRepository=creditRepository;
        this.productoRepository=productoRepository;

        this.creditoService=CreditServiceFactory.getCreditService(config.getClientServiceImpl(),
                creditRepository,
                capitalRepository,
                intrestRepository,
                clienteRepository,
                productoRepository);
    }

    @CrossOrigin
    @PostMapping("credito/creat")
    public CreditEntity createCredito(@RequestBody CreditEntity creditEntity){
        log.debug("creating credit from input: "+creditEntity.toString());
         creditEntity= creditoService.creatCredit(creditEntity);
        return creditEntity;
    }
    @PostMapping("credito/atualiza")
    @CrossOrigin
    public CreditEntity atualizarCredito(@RequestBody CreditEntity creditEntity){

        return creditEntity= creditoService.creatCredit(creditEntity);
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
            ,@RequestParam(name="estado", required = false) CreditoSatus estado
    ) {
        try{
            CreditEntity credito=CreditEntity.builder().id(id).estado(estado).build();

            return creditoService.findByCreditoWithUpPagination(credito,records) ;
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }


    }
  /*  */

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
