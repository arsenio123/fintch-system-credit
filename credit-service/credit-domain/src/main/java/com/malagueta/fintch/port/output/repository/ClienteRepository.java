package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    public List<ClienteEntity>  pesquisarTodosClientes();
    public ClienteEntity findById(Long id);

    ClienteEntity save(ClienteEntity clienteEntity);
}
