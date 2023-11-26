package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.tables.Producto;

public class DTOProduct {
    public static ProductoEntity convertToEntity(Producto producto) {
        ProductoEntity productoEntity = ProductoEntity
                .builder()
                .id(producto.getId())
                .taxa(producto.getTaxa())
                .estado(producto.getEstado())
                .capitalMax(producto.getCapitalMax())
                .capitalMin(producto.getCapitalMin())
                .descricao(producto.getDescricao())
                .intervaloPrestacao(producto.getIntervaloPrestacao())
                .build();
        return productoEntity;
    }
}
