package com.malagueta.fintch.repository.impl.search;

import com.malagueta.fintch.tables.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<Producto, Long> {
}
