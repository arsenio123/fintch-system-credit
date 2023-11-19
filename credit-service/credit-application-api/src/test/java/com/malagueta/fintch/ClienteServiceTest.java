package com.malagueta.fintch;

import com.malagueta.fintch.api.ClienteAPI;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.tables.Cliente;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteAPI clienteAPI;


    @Test
    public void criaCliente(){
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
