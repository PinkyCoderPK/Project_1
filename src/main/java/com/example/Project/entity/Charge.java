package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String chargeName;
    Boolean isMandatory; // Có bắt buộc không
    String description;
    BigDecimal unitAmount; // Phí/Đơn vị
    String unitMeasurement; // Đơn vị đo lường
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
