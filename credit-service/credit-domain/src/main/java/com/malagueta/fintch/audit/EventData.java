package com.malagueta.fintch.audit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class EventData {
    private String eventInput;
    private String eventName;
    private String eventOutput;
    private String sessionId;
    private LocalDateTime eventTime;
    private UUID operationId;
}
