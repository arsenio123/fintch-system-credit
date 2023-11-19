package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.output.repository.ClienteRepository;

public class ClienteServiceImpl implements ClienteService {

    @Override
    public ClienteEntity criarCliente(ClienteEntity clienteEntity,ClienteRepository repository) {
        return repository.save(clienteEntity);
    }
}
