package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.output.repository.ClienteRepository;

public interface ClienteService {
    public ClienteEntity criarCliente(ClienteEntity clienteEntity, ClienteRepository repository);
}
