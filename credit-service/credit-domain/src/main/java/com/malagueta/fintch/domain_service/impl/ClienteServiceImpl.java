package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.port.input.services.ClienteService;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.output.repository.ClienteRepository;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    @Override
    public ClienteEntity criarCliente(ClienteEntity clienteEntity,ClienteRepository clienteRepository) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public List<ClienteEntity> listaClientes(ClienteRepository clienteRepository) {
        return clienteRepository.pesquisarTodosClientes();
    }
}
