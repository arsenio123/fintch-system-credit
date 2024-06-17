package com.malagueta.fintch.services.cliente;

import com.malagueta.fintch.port.input.services.ClienteService;

public class ClienteServiceFactory {
    public static ClienteService getClienteService(String implemetation){
        ClienteService service;
        switch (implemetation){
            case "one": {
                service = new ClienteServiceImpl();
                System.out.println("usando a implementacao  one");
                break;
            }
            default:{
                service = new ClienteServiceImpl();
                System.out.println("usando a implementacao default");
                break;
            }
        }

        return service;
    }
}
