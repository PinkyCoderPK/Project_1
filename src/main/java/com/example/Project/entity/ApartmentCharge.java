package com.example.Project.entity;

import com.example.Project.enums.Enums;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_id", referencedColumnName = "id")
    Charge charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "bill_id", nullable = true)
    private Bill bill;

    BigDecimal chargeAmount;
    BigDecimal unitQuantity;
    BigDecimal amountPaid;
    @Enumerated(EnumType.STRING)
    Enums.PaymentMethod paymentMethod;
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
