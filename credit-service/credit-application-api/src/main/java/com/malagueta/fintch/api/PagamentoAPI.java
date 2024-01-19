package com.malagueta.fintch.api;

import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.domain_service.impl.CapitalServiceDomain;
import com.malagueta.fintch.domain_service.impl.IntrestServiceDomain;
import com.malagueta.fintch.domain_service.impl.PagamentoServiceImpl;
import com.malagueta.fintch.domain_service.impl.factory.PagamentoServiceFactory;
import com.malagueta.fintch.entity.PagamentoEntity;
import com.malagueta.fintch.port.input.services.PagamentoService;
import com.malagueta.fintch.port.output.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class PagamentoAPI {
    Logger log= FintechLogg.getLogger(PagamentoAPI.class);

    private PagamentoService pagamentoService;

    private PagamentoRepository pagamentoRepository;
    private CreditRepository creditoRepository;
    private PrestacaoRepository prestacaoRepository;
    private IntrestServiceDomain intrestServiceDomain;
    private CapitalServiceDomain capitalServiceDomain;
    private AppConfig appConfig;

    PagamentoAPI(AppConfig appConfig,PagamentoRepository pagamentoRepository,
                 CreditRepository creditoRepository,
                 PrestacaoRepository prestacaoRepository,
                 IntrestRepository intrestRepository,
                 CapitalRepository capitalRepository){
        this.appConfig=appConfig;
        this.pagamentoService=PagamentoServiceFactory.getPagamentoService(appConfig.getPagamentoServicoImpl(), pagamentoRepository,
                creditoRepository,
                prestacaoRepository,
                intrestRepository,
                capitalRepository);

    }

    @PostMapping("pagamento/create")
    @CrossOrigin
    public PagamentoEntity createPagamento(@RequestBody PagamentoEntity pagamento){
        log.debug("input: "+pagamento);
        return pagamentoService.fazerPagameto(pagamento);
    }

    @GetMapping("pagamento/lista")
    @CrossOrigin
    public List<PagamentoEntity> getPagamentos(@RequestParam("Prestacao_id") long id_prestacao){
        return pagamentoService.getPayBYprestacao(id_prestacao);
    }

}