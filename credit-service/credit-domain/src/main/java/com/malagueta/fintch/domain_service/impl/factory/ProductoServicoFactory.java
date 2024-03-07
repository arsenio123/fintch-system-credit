package com.malagueta.fintch.domain_service.impl.factory;

import com.malagueta.fintch.domain_service.impl.ProductoServicoImpl;
import com.malagueta.fintch.port.input.services.ProductoServico;
import com.malagueta.fintch.port.output.repository.ProductoRepository;

public class ProductoServicoFactory {
    public static ProductoServico getProductoService(String implemetation, ProductoRepository productoRepository) {

        ProductoServico service;
        switch (implemetation){
            case "one": {
                service = new ProductoServicoImpl(productoRepository);
                System.out.println("usando a implementacao  one");
                break;
            }
            default:{
                service = new ProductoServicoImpl(productoRepository);
                System.out.println("usando a implementacao default one");
                break;
            }
        }

        return service;
    }
}
