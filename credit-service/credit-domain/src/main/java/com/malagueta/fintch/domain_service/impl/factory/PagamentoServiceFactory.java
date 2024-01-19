package com.malagueta.fintch.domain_service.impl.factory;

import com.malagueta.fintch.domain_service.impl.PagamentoServiceImpl;
import com.malagueta.fintch.port.input.services.PagamentoService;
import com.malagueta.fintch.port.output.repository.*;

public class PagamentoServiceFactory {

    public static PagamentoService getPagamentoService(String implemetation,
                                                       PagamentoRepository pagamentoRepository,
                                                       CreditRepository creditoRepository,
                                                       PrestacaoRepository prestacaoRepository,
                                                       IntrestRepository intrestRepository,
                                                       CapitalRepository capitalRepository){

        PagamentoServiceImpl service;
        switch (implemetation){
            case "one": {
                service = new PagamentoServiceImpl(pagamentoRepository,
                        creditoRepository,
                        prestacaoRepository,
                        intrestRepository,
                        capitalRepository);
                System.out.println("usando a implementacao  one");
                break;
            }
            default:{
                service = new PagamentoServiceImpl(pagamentoRepository,
                        creditoRepository,
                        prestacaoRepository,
                        intrestRepository,
                        capitalRepository);
                System.out.println("usando a implementacao default one");
                break;
            }
        }

        return service;

    }
}
