package com.malagueta.fintch.repository.impl.jap;

import com.malagueta.fintch.tables.EventDataRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryJPA extends JpaRepository<EventDataRow,Long > {
}
