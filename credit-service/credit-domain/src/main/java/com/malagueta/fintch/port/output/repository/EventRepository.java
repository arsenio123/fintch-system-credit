package com.malagueta.fintch.port.output.repository;

import com.malagueta.fintch.audit.EventData;

public interface EventRepository {
    void registeEvent(EventData eventData);
}
