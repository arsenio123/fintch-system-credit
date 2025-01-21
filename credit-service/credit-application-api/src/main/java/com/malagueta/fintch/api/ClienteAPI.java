package com.malagueta.fintch.api;

//import com.malagueta.fintch.FintechLogg;
import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.services.cliente.ClienteServiceFactory;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "cliente API", description = "API for Manage clientes")
public class ClienteAPI {


    Logger log=
            LoggerFactory.getLogger(ClienteAPI.class);
   //FintechLogg.getLogger(ClienteAPI.class);


    private ClienteService clienteService;
    private AppConfig appConfig;


    private ClienteRepository clienteRepository;

    public ClienteAPI( ClienteRepository clienteRepository,
                       AppConfig appConfig
    ){
        this.appConfig=appConfig;
       this.clienteService= ClienteServiceFactory.getClienteService(appConfig.getClientServiceImpl());

        this.clienteRepository=clienteRepository;
    }


    @GetMapping("credito/clientes")
    @CrossOrigin
    public List<ClienteEntity> listaClientes(){
        log.info("info iniciando o pedido de lista de clientes");
        return clienteService.listaClientes(clienteRepository);
    }

    @PostMapping("credito/cliente/create")
    @CrossOrigin
    public ClienteEntity creatCliente(@RequestBody ClienteEntity clienteEntity) throws ServiceException {
        log.debug("criating Cliente "+clienteEntity);
        if(clienteEntity.getId()!=0){
            return clienteService.criarCliente(clienteEntity,clienteRepository);
        }else {
            return clienteService.atualizar(clienteEntity, clienteRepository);
        }

    }


    @GetMapping("credito/cliente")
    @CrossOrigin
    public ClienteEntity findClienteById(@RequestParam("client_id") long id){
        return clienteRepository.findById(id);
    }

    @GetMapping("credito/cliente/find")
    @CrossOrigin
    public ClienteEntity findClienteByName(@RequestParam("client_Name") String name){
        return clienteRepository.findByName(name);
    }
}