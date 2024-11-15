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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_id", referencedColumnName = "id")
    private Charge charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    Double chargeAmount;
    Double unitQuantity;
    Double amountPaid;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;

    public double getChargeAmount() {
        return charge.getUnitAmount() * unitQuantity;
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        chargeAmount = charge.getUnitAmount() * unitQuantity;
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        chargeAmount = charge.getUnitAmount() * unitQuantity;
    }

}
