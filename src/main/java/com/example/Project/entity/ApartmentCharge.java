package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String apartmentId;
    String chargeId;
    Double totalAmount;
    Double unitAmount;
    String unitMeasurement;
    Double unitQuantity;
    Double amountPaid;
    Double amountDue;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String status;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;

    public double getTotalAmount() {
        return unitAmount * unitQuantity;
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        totalAmount = unitAmount * unitQuantity;
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        totalAmount = unitAmount * unitQuantity;
    }

}
