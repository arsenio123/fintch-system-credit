package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.entity.ProductoEntity;

import java.util.List;

public interface ProductoRepository {
    ProductoEntity findById(Long id);

    ProductoEntity findProductoById(Long id);

    ProductoEntity preciste(ProductoEntity producto);

    ProductoEntity update(ProductoEntity producto);

    List<ProductoEntity> findByStatus(ProductoEstados status);

    List<ProductoEntity> findAll();

}
