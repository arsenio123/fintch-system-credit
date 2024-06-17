package com.malagueta.fintch.scheduler;

import com.malagueta.fintch.port.output.repository.*;
import com.malagueta.fintch.procedure.PrestacaoProcedure;
import com.malagueta.fintch.procedure.PrestacaoProcedureImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class PrestacaoScheduler {
    private CreditRepository creditRepository;
    private PrestacaoProcedure prestacaoProcedure;
    private PrestacaoRepository prestacaoRepository;
    private CapitalRepository capitalRepository;
    private IntrestRepository intrestRepository;

    private ProductoRepository productoRepository;

    public PrestacaoScheduler(PrestacaoRepository prestacaoRepository,
                              CreditRepository creditRepository,
                              CapitalRepository capitalRepository,
                              IntrestRepository intrestRepository,
                              ProductoRepository productoRepository){
        this.creditRepository=creditRepository;
        this.prestacaoRepository=prestacaoRepository;
        this.capitalRepository=capitalRepository;
        this.intrestRepository=intrestRepository;
        this.productoRepository=productoRepository;
        //pode mudar para ter um factory para abstrair a implementacao
        this.prestacaoProcedure=new PrestacaoProcedureImpl( prestacaoRepository,
                 creditRepository,
                 capitalRepository,
                 intrestRepository,
                 productoRepository);

    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void creatAutomaticPrestacao(){
        prestacaoProcedure.creatAutomatic();
    }
}
