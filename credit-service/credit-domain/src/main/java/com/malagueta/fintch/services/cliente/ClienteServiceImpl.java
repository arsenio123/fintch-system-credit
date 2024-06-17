package com.malagueta.fintch.services.cliente;

import com.malagueta.fintch.services.exception.ServiceException;
import com.malagueta.fintch.services.value.ErrorCatalog;
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

    @Override
    public ClienteEntity atualizar(ClienteEntity clienteEntity, ClienteRepository clienteRepository) throws ServiceException {
        if(clienteEntity.getNumberDoc()==null){
            throw new ServiceException(
                    ErrorCatalog.DATA_DA_AMORTIZACAO_DEVE_SER_MAIOR_QUE_DATA_ATUAL.toString());
        }
        return clienteRepository.atualizar(clienteEntity);
    }
}
