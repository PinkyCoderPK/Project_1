package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String apartmentId;
    Double totalPaymentAmount;
    Double totalAmountPaid;
    Double totalAmountDue;
    LocalDateTime month;
    String status; // Còn thiếu / Trả đủ
    String paymentMethod; // Tiền mặt / Chuyển khoản / Quét thẻ
    LocalDateTime createAt;
    LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    Apartment apartment;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
