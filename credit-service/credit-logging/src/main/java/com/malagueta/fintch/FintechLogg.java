package com.malagueta.fintch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class FintechLogg {
    //@Autowired
    private static Logger log;
    public static Logger getLogger(Class t){
        log=  LoggerFactory.getLogger(FintechLogg.class);
        return log;
    }

    public static Logger getLogger(String name){
        log=  LoggerFactory.getLogger(name);
        return log;
    }

}
