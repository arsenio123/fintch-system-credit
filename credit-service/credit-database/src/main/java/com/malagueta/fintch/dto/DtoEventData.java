package com.malagueta.fintch.dto;

import com.malagueta.fintch.audit.EventData;
import com.malagueta.fintch.tables.EventDataRow;


public class DtoEventData {
    public static EventDataRow convertToRow(EventData eventData) {
        if(eventData==null)
            return null;
        else {
            EventDataRow eventDataRow= new EventDataRow();
            eventDataRow.setEventInput(eventData.getEventInput());
            eventDataRow.setEventOutput(eventData.getEventOutput());
            eventDataRow.setOperationId(eventData.getOperationId());
            eventDataRow.setSessionId(eventData.getSessionId());
            eventDataRow.setEventName(eventData.getEventName());
            return eventDataRow;
        }


    }
}
