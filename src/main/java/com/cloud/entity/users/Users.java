package com.cloud.entity.users;


import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import com.cloud.entity.enums.UserStatus;
import com.cloud.entity.enums.RegistrationType;

@Entity
@Table(
    name = "users",
    indexes = {
        @Index(
            name = "idx_users_status",
            columnList = "status"
        ),
        @Index(
            name = "idx_users_deleted_at",
            columnList = "deleted_at"
        )
    }
)
@SQLDelete(
    sql = """
        UPDATE users
        SET status = 'DELETED',
            deleted_at = CURRENT_TIMESTAMP
        WHERE id = ?
        """
)
@SQLRestriction("deleted_at IS NULL")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "username",
        nullable = false,
        unique = true,
        length = 100
    )
    private String username;

    @Column(
        name = "password_hash",
        nullable = false,
        length = 255
    )
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "status",
        nullable = false,
        length = 30
    )
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "registration_type",
        nullable = false,
        length = 30
    )
    private RegistrationType registrationType =
            RegistrationType.SELF_REGISTERED;

    // Audit fields

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    // Optimistic locking

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}

