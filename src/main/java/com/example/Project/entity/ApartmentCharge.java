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
public class ApartmentCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charge_id", referencedColumnName = "id")
    private Charge charge;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    Bill bill;

    BigDecimal chargeAmount;
    BigDecimal unitQuantity;
    BigDecimal amountPaid;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;


    public void setChargeAmount() {
        if (charge != null && charge.getUnitAmount() != null) {
            this.chargeAmount = charge.getUnitAmount().multiply(unitQuantity);
        }
    }

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        setChargeAmount();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        setChargeAmount();
    }
}
