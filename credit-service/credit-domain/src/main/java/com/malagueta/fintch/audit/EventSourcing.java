package com.malagueta.fintch.audit;

import com.malagueta.fintch.port.output.repository.EventRepository;

public class EventSourcing {
    private EventData eventData;

    public void registEvent(EventData eventData, EventRepository repository){
        repository.registeEvent(eventData);
    }
}
