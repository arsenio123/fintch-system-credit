package com.malagueta.fintch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@PropertySource("classpath:api_config.properties")
public class AppConfig {

    @Value("${domain.client.service.implementation}")
    private String clientServiceImpl;
    @Value("${domain.pagamento.service.implementation}")
    private String pagamentoServiceImpl;

    @Value("${domain.credito.service.implementation}")
    private String creditServiceImpl;


    @Value("${domain.prestacao.service.implementation}")
    private String prestacaoServiceImpl;


    @Value("${domain.producto.service.implementation}")
    private String productoServiceImpl;
    public String getClientServiceImpl() {
        return clientServiceImpl;
    }

    public String getPagamentoServicoImpl() {
        return pagamentoServiceImpl;
    }
    public String getCreditServiceImpl() {
        return creditServiceImpl;
    }
    public String getPrestacaoServiceImpl() {
        return prestacaoServiceImpl;
    }
    public String getProductoServiceImpl() {
        return productoServiceImpl;
    }
}
