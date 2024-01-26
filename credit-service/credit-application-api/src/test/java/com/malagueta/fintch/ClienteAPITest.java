package com.malagueta.fintch;

import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteAPITest {

    private ClienteService clienteService;
    private AppConfig appConfig;


    private ClienteRepository clienteRepository;
    @Test
    public void listaClientes(){

    }

    @Test
    public void creatCliente(){}

    @Test
    public void findClienteById(){}

    @Test
    public void findClienteByName(){}
}
