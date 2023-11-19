package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.ClienteDTO;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import com.malagueta.fintch.repository.impl.search.ClienteRepositoryJpa;
import com.malagueta.fintch.tables.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    private ClienteRepositoryJpa clienteRepositoryJpa;

    ClienteRepositoryImpl(ClienteRepositoryJpa clienteRepositoryJpa){
        this.clienteRepositoryJpa=clienteRepositoryJpa;
    }
    @Override
    public List<ClienteEntity> pesquisarTodosClientes() {
        return ClienteDTO.convertRowToEntity(clienteRepositoryJpa.findAll());
    }

    @Override
    public ClienteEntity findById(Long id) {
        return ClienteDTO.convertRowToEntity(clienteRepositoryJpa.findById(id).get());
    }

    public ClienteEntity save(ClienteEntity entity){
        Cliente clienteRow= ClienteDTO.convertEntityToRow(entity);
        return ClienteDTO.convertRowToEntity(clienteRepositoryJpa.save(clienteRow));
    }

}
