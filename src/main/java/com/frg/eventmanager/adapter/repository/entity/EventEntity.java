package com.frg.eventmanager.adapter.repository.entity;

import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.enums.EventStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Table("events")
public class EventEntity {

    @Id
    @Column("event_id")
    private UUID id;

    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("event_date")
    private LocalDateTime eventDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private EventStatus status;

    @Column("capacity")
    private int capacity;

    @Column("creator_id")
    private UUID creatorId;

    @Column("creator_name")
    private String creatorName;

    public Event toDomain(){
        return new Event(id, title, description, eventDate, createdAt, status, capacity, creatorId, creatorName);
    }

    public static EventEntity fromDomain(Event event){
        return EventEntity.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .createdAt(event.getCreatedAt())
                .status(event.getStatus())
                .capacity(event.getCapacity())
                .creatorId(event.getCreatorId())
                .creatorName(event.getCreatorName())
                .build();
    }

}
