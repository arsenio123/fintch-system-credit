package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.ClienteDTO;
import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.port.output.repository.ClienteRepository;
import com.malagueta.fintch.repository.impl.jap.ClienteRepositoryJpa;
import com.malagueta.fintch.tables.Cliente;
import jakarta.transaction.Transactional;
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
        return ClienteDTO.convertToEntitys(clienteRepositoryJpa.findAll());
    }

    @Override
    public ClienteEntity findById(Long id) {
        return ClienteDTO.convertToEntity(clienteRepositoryJpa.findById(id).orElse(null));
    }

    @Transactional
    public ClienteEntity save(ClienteEntity entity){
        Cliente clienteRow= ClienteDTO.convertToRow(entity);
        return ClienteDTO.convertToEntity(clienteRepositoryJpa.save(clienteRow));
    }

    @Override
    public ClienteEntity findByName(String name) {
        return ClienteDTO.convertToEntity(clienteRepositoryJpa.findByNome(name));
    }

}
