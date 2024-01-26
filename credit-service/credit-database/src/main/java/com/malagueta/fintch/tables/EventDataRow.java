package com.malagueta.fintch.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
public class EventDataRow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "event_input" ,length = 3000)
    private String eventInput;

    private String eventName;

    @Column(name = "event_output" ,length = 3000)
    private String eventOutput;

    private String sessionId;

    private LocalDateTime eventTime;

    private UUID operationId;
}
