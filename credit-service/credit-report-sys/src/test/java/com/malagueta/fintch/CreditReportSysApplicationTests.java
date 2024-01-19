package com.malagueta.fintch;

import com.malagueta.fintch.port.output.repository.CreditRepository;
import com.malagueta.fintch.report.cron.Report;
import com.malagueta.fintch.report.cron.ReportSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CreditReportSysApplicationTests {
    @Autowired
    private ReportSchedular schedular;

    @Test
    void contextLoads() {
        schedular.sendTODelinquent();
    }

    @Test
    void reportDeliquenenciaTest(){
        //Assert.notEmpty(report.creditosEmDelinquencia(),"Nao pode ter valor");
    }

}
