package com.example.creditlogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class CreditLoggingApplication {

    public static Logger log= Logger.getLogger("test");

    public static void main(String[] args) {

        SpringApplication.run(CreditLoggingApplication.class, args);
       // log.log(Level.WARNING,"testando os logges para a aplicacao isso e infrastrutura tal qual base de dados");

    }

}
