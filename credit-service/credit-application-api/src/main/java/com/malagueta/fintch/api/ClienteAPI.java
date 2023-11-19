package com.malagueta.fintch.api;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.domain_service.impl.ClienteServiceImpl;
import com.malagueta.fintch.dto.ClienteDTO;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import com.malagueta.fintch.tables.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteAPI {

    Logger log= LoggerFactory.getLogger(ClienteAPI.class);


    private ClienteService clienteService =new ClienteServiceImpl();


    private ClienteRepository repository;

    public ClienteAPI( ClienteRepository repository){
        this.repository=repository;
    }


    @GetMapping("credito/clientes")
    @CrossOrigin
    public List<Cliente> listaClientes(){

        return ClienteDTO.convertEntityToRow(repository.pesquisarTodosClientes());
    }

    @PostMapping("credito/cliente/create")
    @CrossOrigin
    public ClienteEntity creatCliente(@RequestBody ClienteEntity clienteEntity){
        log.debug("criating Cliente "+clienteEntity);
        return clienteService.criarCliente(clienteEntity,repository);
    }

    @GetMapping("credito/cliente")
    @CrossOrigin
    public ClienteEntity findClienteById(@RequestParam("client_id") long id){
        return repository.findById(id);
    }
}