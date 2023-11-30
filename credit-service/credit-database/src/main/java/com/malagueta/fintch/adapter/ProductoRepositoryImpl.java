package com.malagueta.fintch.adapter;

import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.dto.DTOProduct;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.output.repository.ProductoRepository;
import com.malagueta.fintch.repository.impl.jap.ProductRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ProductoEntity findProductoById(Long id) {
        return DTOProduct.convertToEntity(repositoryJpa.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public ProductoEntity preciste(ProductoEntity productoEntity) {
        return DTOProduct.convertToEntity(
                repositoryJpa
                        .save(DTOProduct
                                .convertToRow(productoEntity))
        );
    }

    @Transactional
    @Override
    public ProductoEntity update(ProductoEntity productoEntity) {
        return preciste(productoEntity);
    }

    @Override
    public List<ProductoEntity> findByStatus(ProductoEstados status) {
        return null;
    }

    @Override
    public List<ProductoEntity> findAll() {
        List<ProductoEntity> productoEntities= new ArrayList<>();
         repositoryJpa.findAll().stream().forEach(producto ->{
                productoEntities.add(DTOProduct.convertToEntity(producto));
        } );
        return productoEntities;
    }

}
