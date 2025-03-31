package com.frg.eventmanager.domain.entity;

import com.frg.eventmanager.domain.enums.SubscriptionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {

    private UUID id;
    private UUID userId;
    private UUID eventId;
    private LocalDateTime subscribedAt;
    private SubscriptionStatus status;

    public Subscription(UUID id, UUID userId, UUID eventId, LocalDateTime subscribedAt, SubscriptionStatus status) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.subscribedAt = subscribedAt;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }
    public UUID getUserId() {
        return userId;
    }
    public UUID getEventId() {
        return eventId;
    }
    public LocalDateTime getSubscribedAt() {
        return subscribedAt;
    }
    public SubscriptionStatus getStatus() {
        return status;
    }

    public void cancelSubscription() {
        if(this.status == SubscriptionStatus.CONFIRMED) {
            this.status = SubscriptionStatus.CANCELLED;
        }
    }
}
