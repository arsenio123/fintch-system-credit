package com.malagueta.fintch;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreditLoggingApplicationTests {
    @Autowired
    private FintechLogg fintechLogg;


    @Test
    void logFactoryTest() {
        Logger logger= FintechLogg.getLogger("TestesLogges");
        logger.debug("logando debug");
    }

    @Test
    void annotationLoggingTest() {
        //anottationLog.logging();
    }


}
