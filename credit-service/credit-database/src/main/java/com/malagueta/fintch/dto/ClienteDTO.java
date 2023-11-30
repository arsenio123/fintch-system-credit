package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.tables.Cliente;

import java.util.List;

public class ClienteDTO {
    public static List<Cliente> convertEntityToRow(List<ClienteEntity> pesquisarTodosClientes) {
        return null;
    }

    public static ClienteEntity convertRowToEntity(Cliente cliente) {
        if(cliente!=null){
            ClienteEntity clienteEntity=new ClienteEntity();
            clienteEntity.setId(cliente.getId());
            clienteEntity.setIdDoc(cliente.getIdDoc());
            clienteEntity.setEmail(cliente.getEmail());
            clienteEntity.setNumberDoc(cliente.getNumberDoc());
            clienteEntity.setNome(cliente.getNome());
            clienteEntity.setMorada(cliente.getMorada());
            clienteEntity.setRendimento(cliente.getRendimento());
            clienteEntity.setTelefone(cliente.getTelefone());
            clienteEntity.setSetor(cliente.getSetor());
            clienteEntity.setDataNascimento(cliente.getDataNascimento());
        return clienteEntity;
        }
        return null;
    }

    public static List<ClienteEntity> convertRowToEntity(List<Cliente> clientes) {
        return null;
    }
    public static Cliente convertEntityToRow(ClienteEntity clienteEntity) {

        if(clienteEntity!=null){
            Cliente rowCliente= new Cliente();
            rowCliente.setId(clienteEntity.getId());
            rowCliente.setIdDoc(clienteEntity.getIdDoc());
            rowCliente.setDataNascimento(clienteEntity.getDataNascimento());
            rowCliente.setMorada(clienteEntity.getMorada());
            rowCliente.setEmail(clienteEntity.getEmail());
            rowCliente.setTelefone(clienteEntity.getTelefone());
            rowCliente.setNome(clienteEntity.getNome());
            rowCliente.setRendimento(clienteEntity.getRendimento());
            rowCliente.setSetor(clienteEntity.getSetor());
            rowCliente.setNumberDoc(clienteEntity.getNumberDoc());
            return rowCliente;
        }
        return null;
    }
}
