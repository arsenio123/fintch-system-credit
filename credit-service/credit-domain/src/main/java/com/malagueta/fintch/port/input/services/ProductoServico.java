package com.malagueta.fintch.port.input.services;

import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.entity.ProductoEntity;

import java.util.List;

public interface ProductoServico {
    public ProductoEntity creat(ProductoEntity producto);

    public ProductoEntity atualizar(ProductoEntity producto);

    public List<ProductoEntity> listarPorEstado(ProductoEstados status);

    public List<ProductoEntity> listarTodos();
}
