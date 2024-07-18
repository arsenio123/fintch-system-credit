package com.malagueta.fintch;

import com.malagueta.fintch.api.ClienteAPI;
import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.entity.ClienteEntity;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ControllerApp.class)
public class ClienteServiceTest {

    @Autowired
    private ClienteAPI clienteAPI;


    @Test
    public void criaCliente() throws ServiceException {
        ClienteEntity cliente=clienteAPI.creatCliente(new ClienteEntity());
        Assert.assertTrue(cliente.getId()>0);
    }

    @Test
    public void pesquisaCliente(){
        ClienteEntity cliente=clienteAPI.findClienteById(1l);
        System.out.println(cliente.toString());
        Assert.assertTrue(cliente.getId()>0);
    }
}
