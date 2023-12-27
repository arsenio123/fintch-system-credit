package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.ClienteEntity;
import com.malagueta.fintch.tables.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
    public static List<Cliente> convertToRows(List<ClienteEntity> ClienteEntitys) {
        if(ClienteEntitys ==null)
        return null;
        List<Cliente> clientes=new ArrayList<Cliente>();
        ClienteEntitys.stream().forEach(clienteEntity -> {
            clientes.add(ClienteDTO.convertToRow(clienteEntity));
        });
        return clientes;
    }

    public static ClienteEntity convertToEntity(Cliente cliente) {
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

    public static List<ClienteEntity> convertToEntitys(List<Cliente> clientes) {
        if(clientes==null)
        return null;
        List<ClienteEntity> clienteEntities=new ArrayList<ClienteEntity>();
        clientes.stream().forEach(clinte ->{
            clienteEntities.add(ClienteDTO.convertToEntity(clinte));
        });
        return clienteEntities;
    }
    public static Cliente convertToRow(ClienteEntity clienteEntity) {

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
