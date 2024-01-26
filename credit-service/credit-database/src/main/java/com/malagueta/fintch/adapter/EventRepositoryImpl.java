package com.malagueta.fintch.adapter;

import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.dto.DtoEventData;
import com.malagueta.fintch.port.output.repository.EventRepository;
import com.malagueta.fintch.repository.impl.jap.EventRepositoryJPA;
import com.malagueta.fintch.tables.EventDataRow;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private EventRepositoryJPA eventRepositoryJPA;
    public EventRepositoryImpl(EventRepositoryJPA eventRepositoryJPA){
        this.eventRepositoryJPA=eventRepositoryJPA;
    }
    @Override
    public void registeEvent(EventData eventData) {
        EventDataRow eventDataRow= DtoEventData.convertToRow(eventData);
        eventDataRow.setEventTime(LocalDateTime.now());
        eventRepositoryJPA.save(eventDataRow);
    }
}
