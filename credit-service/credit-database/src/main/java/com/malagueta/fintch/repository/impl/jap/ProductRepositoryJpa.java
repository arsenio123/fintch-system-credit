package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.tables.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<Producto, Long> {
    public List<Producto> findProductosByEstado(ProductoEstados estado);
}
