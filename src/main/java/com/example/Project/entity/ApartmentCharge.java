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

    @ManyToOne
    @JoinColumn(name = "chargeId", referencedColumnName = "id", insertable = false, updatable = false)
    Charge charge;
    String apartmentId;
    String chargeId;
    Double chargeAmount;
    Double amountPaid;
    Double amountDue;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;


    public void calculateChargeAmount() {
        if (charge != null && charge.getUnitAmount() != null && charge.getUnitQuantity() != null) {
            this.chargeAmount = charge.getUnitAmount() * charge.getUnitQuantity() ;
        }
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        calculateChargeAmount();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        calculateChargeAmount();
    }

}
