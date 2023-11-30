package com.malagueta.fintch.api;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.impl.CreditServiceImpl;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.port.output.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class CreditoAPI {

    private static Logger log= LoggerFactory.getLogger(CreditoAPI.class);
    //Deve ser colocado on fly

   // @Autowired
    private  CreditRepository creditRepository;
    private CreditService creditoService;
    //@Autowired
    private CapitalRepository capitalRepository;
    //@Autowired
    private IntrestRepository intrestRepository;
    //@Autowired
    private ClienteRepository clienteRepository;
    //@Autowired
    private ProductoRepository productoRepository;


    public CreditoAPI(CreditRepository creditRepository,
                      CapitalRepository capitalRepository,
                      IntrestRepository intrestRepository,
                      ClienteRepository clienteRepository,
                      ProductoRepository productoRepository){
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.creditRepository=creditRepository;
        this.productoRepository=productoRepository;

        this.creditoService=new CreditServiceImpl(creditRepository,
                capitalRepository,
                intrestRepository,
                clienteRepository,
                productoRepository);
    }

    @CrossOrigin
    @PostMapping("credito/creat")
    public CreditEntity createCredito(@RequestBody CreditEntity creditEntity){
        log.debug(creditEntity.toString());
         creditEntity= creditoService.creatCredit(creditEntity);
        return creditEntity;
    }
    @PostMapping("credito/atualiza")
    @CrossOrigin
    public CreditEntity atualizarCredito(@RequestBody CreditEntity creditEntity){

        return creditEntity= creditoService.creatCredit(creditEntity);
    }

/*
    @GetMapping ("MS/credito/list")
    public List<CreditEntity> listCredito(){
        return null;
    }
    @GetMapping("credito/list/critirea")
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROLE_CREDITOS')")
    public List<CreditEntity> getbyCritirea(@RequestParam(name="estado" ,required = false) CreditoSatus estado,
                                       @RequestParam(name="minBeginDate", required = false) Date minBeginDate,
                                       @RequestParam(name="maxBeginDate", required = false)Date maxBeginDate,
                                       @RequestParam(name="valor", required = false) long valor) {
        return creditoService.listarPorCreterio(estado,minBeginDate,maxBeginDate,valor);

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
    public List<CreditEntity> findByCreditoWithPagination(
            @RequestParam(name="id" ,required = false) long id
            ,@RequestParam(name="records", required = false) int records
            ,@RequestParam(name="estado", required = false) CreditoSatus estado
    ) {
        try{
            CreditEntity credito=new Credito();
            credito.setId(id)
                    .setEstado(estado);

            return creditoService.findByCreditoWithUpPagination(credito,records) ;
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }


    }
    */

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
