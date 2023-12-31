package com.malagueta.fintch;

import com.malagueta.fintch.port.input.services.CreditService;
import com.malagueta.fintch.domain_service.impl.CreditServiceImpl;
import com.malagueta.fintch.domain_service.value.CreditoSatus;
import com.malagueta.fintch.entity.CreditEntity;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.output.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;

@SpringBootTest
public class CriateCreditoTest {
    @Autowired
    private CreditRepository creditRepository;
    private CreditService service =new CreditServiceImpl();

    @Test
    public void testCreatCredito(){
       ProductoEntity producto= ProductoEntity.builder().id(1l)
                        .capitalMin(10)
                        .capitalMax(100000)
                        .descricao("testing product")
                        .intervaloPrestacao(30).build();


        ;
        CreditEntity credit=CreditEntity.builder().jurus(13)
                        .doDate(ZonedDateTime.now().plusDays(60))
                                .creatDate(ZonedDateTime.now())
                                        .estado(CreditoSatus.PENDENTE)
                                                .valor(1000)
                                                        .producto(producto).build();



        Assert.isNull(service.creatCredit(credit,creditRepository),"tem que devolver null a principio");
    }
}
