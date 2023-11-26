package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.entity.ProductoEntity;

public interface ProductoRepository {
    ProductoEntity findById(Long id);
}
