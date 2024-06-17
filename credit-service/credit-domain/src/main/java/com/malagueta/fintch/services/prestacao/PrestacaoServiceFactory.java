package com.malagueta.fintch.services.prestacao;

import com.malagueta.fintch.services.prestacao.PrestacaoServiceImpl;
import com.malagueta.fintch.port.input.services.PrestacaoService;

public class PrestacaoServiceFactory {

    public static PrestacaoService getPrestacaoService(String implemetation){

        PrestacaoService service;
        switch (implemetation){
            case "one": {
                service = new PrestacaoServiceImpl();
                System.out.println("usando a implementacao  one");
                break;
            }
            default:{
                service = new PrestacaoServiceImpl();
                System.out.println("usando a implementacao default one");
                break;
            }
        }

        return service;

    }

}
