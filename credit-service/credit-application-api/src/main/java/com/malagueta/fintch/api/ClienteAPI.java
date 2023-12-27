package com.malagueta.fintch.api;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.domain_service.impl.ClienteServiceImpl;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteAPI {

    Logger log= LoggerFactory.getLogger(ClienteAPI.class);


    private ClienteService clienteService;


    private ClienteRepository clienteRepository;

    public ClienteAPI( ClienteRepository clienteRepository){
        this.clienteService=new ClienteServiceImpl();
        this.clienteRepository=clienteRepository;
    }


    @GetMapping("credito/clientes")
    @CrossOrigin
    public List<ClienteEntity> listaClientes(){

        return clienteService.listaClientes(clienteRepository);
    }

    @PostMapping("credito/cliente/create")
    @CrossOrigin
    public ClienteEntity creatCliente(@RequestBody ClienteEntity clienteEntity){
        log.debug("criating Cliente "+clienteEntity);
        return clienteService.criarCliente(clienteEntity,clienteRepository);
    }

    @GetMapping("credito/cliente")
    @CrossOrigin
    public ClienteEntity findClienteById(@RequestParam("client_id") long id){
        return clienteRepository.findById(id);
    }
}