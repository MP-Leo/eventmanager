package com.frg.eventmanager.adapter.repository.entity;

import com.frg.eventmanager.domain.entity.User;
import com.frg.eventmanager.domain.enums.UserRole;
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
@Table("users")
public class UserEntity {

    @Id
    @Column("user_id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("role")
    private UserRole role;

    @Column("created_at")
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
