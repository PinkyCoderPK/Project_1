package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String apartmentName;
    private Integer floorNumber;
    private Integer apartmentNumber;
    private Double area;
    private String ownerId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
