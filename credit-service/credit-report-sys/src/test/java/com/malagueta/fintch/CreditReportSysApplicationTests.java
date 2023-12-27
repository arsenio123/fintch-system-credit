package com.malagueta.fintch;

import com.malagueta.fintch.port.output.repository.CreditRepository;
import com.malagueta.fintch.report.cron.Report;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CreditReportSysApplicationTests {
    @Autowired
    private Report report;

    @Test
    void contextLoads() {

        report.creditosEmDelinquencia();
    }

    @Test
    void reportDeliquenenciaTest(){
        Assert.notEmpty(report.creditosEmDelinquencia(),"Nao pode ter valor");
    }

}
