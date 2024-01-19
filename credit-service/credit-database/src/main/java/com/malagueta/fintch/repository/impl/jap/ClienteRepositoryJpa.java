package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.tables.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long> {
    public Cliente findByNome(String nome);

}
