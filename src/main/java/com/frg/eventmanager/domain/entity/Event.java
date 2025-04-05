package com.frg.eventmanager.domain.entity;

import com.frg.eventmanager.domain.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private LocalDateTime createdAt;
    private EventStatus status;
    private int capacity;

    private UUID creatorId;
    private String creatorName;

    public Event(UUID id, String title, String description, LocalDateTime eventDate, LocalDateTime createdAt, EventStatus status, int capacity, UUID creatorId, String creatorName) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.eventDate = eventDate;
            this.createdAt = createdAt;
            this.status = status;
            this.capacity = capacity;
            this.creatorId = creatorId;
            this.creatorName = creatorName;
    }

    public Event(){

    }

    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public EventStatus getStatus() {
        return status;
    }
    public int getCapacity() {
        return capacity;
    }

    public UUID getCreatorId() {
        return creatorId;
    }
    public String getCreatorName() {
        return creatorName;
    }

    public void cancelEvent() {
        if(this.status != EventStatus.CANCELLED) {
            this.status = EventStatus.CANCELLED;
        }
    }

    public boolean isActive() {
        return this.status == EventStatus.ACTIVE;
    }
}
