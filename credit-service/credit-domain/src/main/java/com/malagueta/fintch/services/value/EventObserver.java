package com.malagueta.fintch.services.value;


import com.malagueta.fintch.services.exception.ServiceException;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public abstract class EventObserver implements ObserverContract{

    protected static List<EventHandler> OBSERVALS= new ArrayList<>();
    @SneakyThrows
    public static void fire(Object event) {
        List<EventHandler> eventHandlers=OBSERVALS;
        for (EventHandler eventHandler:eventHandlers) {
            try {
                eventHandler.action(event);
            }catch (ServiceException exception){
                //loga a excepcao
                exception.printStackTrace();
                throw  exception;
            }
        }
    }


}
