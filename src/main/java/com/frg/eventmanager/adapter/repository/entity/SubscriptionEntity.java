package com.frg.eventmanager.adapter.repository.entity;

import com.frg.eventmanager.domain.entity.Subscription;
import com.frg.eventmanager.domain.enums.SubscriptionStatus;
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
@Table("subscriptions")
public class SubscriptionEntity {

    @Id
    @Column("subscription_id")
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column("event_id")
    private UUID eventId;

    @Column("subscribed_at")
    private LocalDateTime subscribedAt;

    @Column("status")
    private SubscriptionStatus status;

    public Subscription toDomain(){
        return new Subscription(id, userId, eventId, subscribedAt, status);
    }

    public static SubscriptionEntity fromDomain(Subscription subscription){
        return SubscriptionEntity.builder()
                .id(subscription.getId())
                .userId(subscription.getUserId())
                .eventId(subscription.getEventId())
                .subscribedAt(subscription.getSubscribedAt())
                .status(subscription.getStatus())
                .build();
    }
}
