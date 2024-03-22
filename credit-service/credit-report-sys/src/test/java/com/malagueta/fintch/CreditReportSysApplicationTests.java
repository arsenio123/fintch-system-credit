package com.malagueta.fintch;

import com.malagueta.fintch.report.cron.ReportExecuter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreditReportSysApplicationTests {
    @Autowired
    private ReportExecuter executer;

    @Test
    void contextLoads() {
        executer.sendTODelinquent();
    }

    @Test
    void reportDeliquenenciaTest(){
        //Assert.notEmpty(report.creditosEmDelinquencia(),"Nao pode ter valor");
    }

}
