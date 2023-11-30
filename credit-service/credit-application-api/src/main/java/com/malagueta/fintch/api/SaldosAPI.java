package com.malagueta.fintch.api;

import com.malagueta.fintch.domain_service.impl.CapitalServiceDomain;
import com.malagueta.fintch.domain_service.impl.CreditServiceImpl;
import com.malagueta.fintch.domain_service.impl.IntrestServiceDomain;
import com.malagueta.fintch.entity.CapitalEntity;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.IntrestEntity;
import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.port.output.repository.CapitalRepository;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import com.malagueta.fintch.port.output.repository.IntrestRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaldosAPI {
    private IntrestServiceDomain intrestServiceDomain;
    private CapitalServiceDomain capitalServiceDomain;
    private CreditService creditoService;

    private CapitalRepository capitalRepository;

    private CreditRepository creditRepository;

    private IntrestRepository intrestRepository;


    public SaldosAPI(CapitalRepository capitalRepository,
              CreditRepository creditRepository,
              IntrestRepository intrestRepository) {

        this.capitalServiceDomain = new CapitalServiceDomain(capitalRepository);
        this.intrestServiceDomain = new IntrestServiceDomain(intrestRepository);
        this.creditoService = new CreditServiceImpl(creditRepository);
    }

    @GetMapping("saldo/intrest")
    @CrossOrigin
    public IntrestEntity getIntrest(@RequestParam("credito_id") Long credito_id) {

        return intrestServiceDomain.getLast(credito_id);
    }

    @GetMapping("saldo/capital")
    @CrossOrigin
    public CapitalEntity getCapital(@RequestParam("credito_id") Long credito_id) {
        CreditEntity credito = creditoService.findCreditoByID(credito_id);
        CapitalEntity capitalEntity = capitalServiceDomain.getLast(credito);
        System.out.println(capitalEntity);
        return capitalEntity;
    }

}

