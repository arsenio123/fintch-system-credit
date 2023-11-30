package com.malagueta.fintch.domain_service.impl;

import com.malagueta.fintch.domain_service.value.ErrorCatalog;
import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.input.services.ProductoServico;
import com.malagueta.fintch.port.output.repository.ProductoRepository;
import java.util.List;

public class ProductoServicoImpl implements ProductoServico {

    ProductoRepository productoRepository;

    public ProductoServicoImpl(ProductoRepository productoRepository){
        this.productoRepository=productoRepository;
    }
    @Override
    public ProductoEntity creat(ProductoEntity productoEntity) {
        productoEntity.setEstado(ProductoEstados.PENDENTE);
        validaCredito(productoEntity);
        return productoRepository.preciste(productoEntity);
    }

    @Override
    public ProductoEntity atualizar(ProductoEntity producto) {
        validaFluxoEstados(producto);
        return productoRepository.update(producto);
    }

    @Override
    public List<ProductoEntity> listarPorEstado(ProductoEstados status) {
        return productoRepository.findByStatus(status);
    }

    @Override
    public List<ProductoEntity> listarTodos() {
        return productoRepository.findAll();
    }

    public void validaFluxoEstados(ProductoEntity producto){

        ProductoEntity findedProduct= productoRepository.findProductoById(producto.getId());
        if(findedProduct.getEstado()==ProductoEstados.EXPIRADO)
            throw  new RuntimeException(ErrorCatalog.EXPIRED_PRODUCT_CANT_BE_NORMAL.getMessage());
        if(findedProduct.getEstado()==ProductoEstados.NORMAL && producto.getEstado()==ProductoEstados.PENDENTE)
            throw  new RuntimeException(ErrorCatalog.NORMAL_PRODUCT_CANT_BE_PENDING.getMessage());
        if(findedProduct.getCapitalMin()< Double.valueOf(0)){
            throw  new RuntimeException(ErrorCatalog.VALOR_DO_PRODUCTO_MENOR_QUE_ZERO.getMessage());
        }
    }

    public void validaCredito(ProductoEntity productoEntity){

        if(productoEntity.getCapitalMin()< Double.valueOf(0)){
            throw  new RuntimeException(ErrorCatalog.VALOR_DO_PRODUCTO_MENOR_QUE_ZERO.getMessage());
        }
    }
}
