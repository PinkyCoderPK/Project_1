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
    Double chargeAmount;
    Double unitAmount;
    String unitMeasurement;
    Double unitQuantity;
    Double amountPaid;
    Double amountDue;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;

    public double getChargeAmount() {
        return unitAmount * unitQuantity;
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        chargeAmount = unitAmount * unitQuantity;
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        chargeAmount = unitAmount * unitQuantity;
    }

}
