package com.malagueta.fintch.adapter;

import com.malagueta.fintch.dto.DTOProduct;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.output.repository.ProductoRepository;
import com.malagueta.fintch.repository.impl.search.ProductRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    ProductRepositoryJpa repositoryJpa;

    public ProductoRepositoryImpl(ProductRepositoryJpa repositoryJpa){
        this.repositoryJpa=repositoryJpa;
    }
    @Override
    public ProductoEntity findById(Long id) {
        return DTOProduct.convertToEntity(repositoryJpa.findById(id).orElse(null));
    }
}
