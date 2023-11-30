package com.malagueta.fintch.repository;




import com.malagueta.fintch.tables.Cliente;
import com.malagueta.fintch.tables.Credito;

import java.util.List;

public interface CreditoRepositoryDB {

    public List<Credito> getAllCredit();
    public Credito createCredito(Credito credito);

    List<Credito> findOpenCredit(Cliente cliente);

    Credito atulizaCredito(Credito setEstado);

    List<Credito> findCreditoWithPagination(Credito credito, int records);

    List<Credito> findCreditoWithPaginationPrevies(Credito credito, int records);
}
