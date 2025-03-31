package com.frg.eventmanager.infrastructure.adapter.repository.entity;

import com.frg.eventmanager.domain.entity.User;
import com.frg.eventmanager.domain.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Table("users")
public class UserEntity {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;


    public User toDomain() {
        return new User(id, name, email, password, role, createdAt);
    }

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
