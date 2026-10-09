package com.cloud.entity.users;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import lombok.Data;

import com.cloud.entity.users.Users;

@Entity
@Table(
    name = "profiles",
    indexes = {
        @Index(
            name = "idx_profiles_email",
            columnList = "email"
        ),
        @Index(
            name = "idx_profiles_phone",
            columnList = "phone"
        )
    }
)
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * One User has one Profile.
     *
     * user_id is the foreign key to users.id.
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private Users user;

    @Column(
        name = "first_name",
        length = 100
    )
    private String firstName;

    @Column(
        name = "last_name",
        length = 100
    )
    private String lastName;

    @Column(
        name = "email",
        unique = true,
        length = 255
    )
    private String email;

    @Column(
        name = "phone",
        unique = true,
        length = 20
    )
    private String phone;

    // Audit fields

    @Column(
        name = "created_at",
        nullable = false
    )
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(
        name = "updated_at",
        nullable = false
    )
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    // Optimistic locking

    @Version
    @Column(
        name = "version",
        nullable = false
    )
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

