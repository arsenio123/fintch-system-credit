package com.malagueta.fintch.api;



import com.malagueta.fintch.domain_service.impl.PrestacaoServiceImpl;
import com.malagueta.fintch.domain_service.value.Estado;
import com.malagueta.fintch.entity.PrestacaoEntity;
import com.malagueta.fintch.port.input.services.PrestacaoService;
import com.malagueta.fintch.port.output.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j(topic = "PrestacoesAPI")
@RestController
public class PrestacoesAPI {

    private PrestacaoRepository prestacaoRepository;
    private IntrestRepository intrestRepository;
    private CapitalRepository capitalRepository;
    private CreditRepository creditRepository;

    private ProductoRepository productoRepository;


    /**
     * funcionalite should only be accessed from service, not form repository directet becouse you can miss logic
     */
    private PrestacaoService prestacaoService;


    public PrestacoesAPI( PrestacaoRepository prestacaoRepository,
     IntrestRepository intrestRepository,
     CapitalRepository capitalRepository,
     CreditRepository creditRepository,
     ProductoRepository productoRepository){

        this.prestacaoRepository=prestacaoRepository;
        this.intrestRepository=intrestRepository;
        this.capitalRepository=capitalRepository;
        this.creditRepository=creditRepository;
        this.productoRepository=productoRepository;

        this.prestacaoService=new PrestacaoServiceImpl();
    }

    @GetMapping("prestacao/list")
    @CrossOrigin
    public List<PrestacaoEntity> listar (Long creditID, Estado Status, LocalDate begin, LocalDate end){
        return prestacaoService.listar(creditID,Status,begin,end,prestacaoRepository);
    }

    @GetMapping("prestacao/list/credit_id")
    @CrossOrigin
    public List<PrestacaoEntity> listar (@Param("id") long id){
        log.info("Pedido Prestacao para o Credito: numero="+id);
        List<PrestacaoEntity>  output= prestacaoService.listar(id,null,null,null,prestacaoRepository);
        log.info("Output: "+output);
        return output;
    }

    @PostMapping("prestacao/create")
    @CrossOrigin
    public PrestacaoEntity create(@RequestBody PrestacaoEntity prestacaoEntity){
        log.info("criando a prestacao: "+prestacaoEntity);
        return prestacaoService.create(prestacaoEntity,
                capitalRepository,
                intrestRepository,
                prestacaoRepository,
                creditRepository,
                productoRepository) ;
    }

    @GetMapping("prestacao/list/id")
    @CrossOrigin
    public PrestacaoEntity getPrestacao (@Param("prestacao_ID") long id){
        log.info("Pedido Prestacao para o Credito: numero="+id);
        PrestacaoEntity output=prestacaoService.listarPorID(id, prestacaoRepository);
        log.info("Output: "+output);
        return output;
    }

}

