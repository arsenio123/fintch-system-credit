package com.malagueta.fintch.scheduler;


import com.malagueta.fintch.App;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;


@SpringBootTest
@ContextConfiguration(classes = App.class)
public class PrestacaoSchedulerTest {
    private Logger log= LoggerFactory.getLogger(PrestacaoSchedulerTest.class);

    @Autowired
    private PrestacaoScheduler prestacaoScheduler;


    @Test
    public void creatPrestacaoAutomatic(){
        log.info("iniciando o procedimento de criacao Automatica de Prestacoes");
        //prestacaoScheduler.creatAutomaticPrestacao();
        Assert.notEmpty(new ArrayList<>(),"Deve devolver os investimentos por regularizar o intrest");
    }

}
