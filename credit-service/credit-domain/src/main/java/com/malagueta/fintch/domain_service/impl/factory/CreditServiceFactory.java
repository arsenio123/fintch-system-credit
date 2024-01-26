package com.malagueta.fintch.domain_service.impl.factory;

import com.malagueta.fintch.domain_service.impl.CreditServiceImpl;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.port.output.repository.*;

public class CreditServiceFactory {
    public static CreditService getCreditService(String implemetation,
                                                   CreditRepository creditRepository,
                                                   CapitalRepository capitalRepository,
                                                   IntrestRepository intrestRepository,
                                                   ClienteRepository clienteRepository,
                                                   ProductoRepository productoRepository,
                                                 EventRepository eventRepository){
        CreditService service;
        switch (implemetation){
            case "one": {
                service = new CreditServiceImpl(creditRepository,
                        capitalRepository,
                        intrestRepository,
                        clienteRepository,
                        productoRepository,
                        eventRepository);
                System.out.println("usando a implementacao  one");
                break;
            }
            default:{
                service = new CreditServiceImpl(creditRepository,
                        capitalRepository,
                        intrestRepository,
                        clienteRepository,
                        productoRepository,
                        eventRepository);
                System.out.println("usando a implementacao  default");
                break;
            }
        }

        return service;
    }

}
