package com.malagueta.fintch.dto;

import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.tables.Producto;
import org.jetbrains.annotations.NotNull;

public class DTOProduct {
    public static ProductoEntity convertToEntity(Producto producto) {
        try {
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
        }catch (NullPointerException ex){
            return null;
        }

    }

    public static Producto convertToRow( ProductoEntity productoEntity) {
        if(productoEntity==null)
            return null;
        Producto producto= new Producto();
        producto.setId(productoEntity.getId())
                .setCapitalMax(productoEntity.getCapitalMax())
                .setDescricao(productoEntity.getDescricao())
                .setTaxa(productoEntity.getTaxa())
                .setCapitalMin(productoEntity.getCapitalMin())
                .setEstado(productoEntity.getEstado());
        return producto;
    }
}
