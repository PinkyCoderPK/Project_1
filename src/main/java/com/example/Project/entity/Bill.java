package com.example.Project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

//    @ManyToOne
//    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
//    Apartment apartment;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ApartmentCharge> apartmentChargeList;

    BigDecimal totalPaymentAmount;
    BigDecimal totalAmountPaid;
    BigDecimal totalAmountDue;
    LocalDateTime month;
    String status; // Còn thiếu / Trả đủ
    String paymentMethod; // Tiền mặt / Chuyển khoản / Quét thẻ
    LocalDateTime createAt;
    LocalDateTime updateAt;

    // tinh totalPaymentAmount
    public void totalPayment() {
        if (apartmentChargeList == null || apartmentChargeList.isEmpty()) {
            totalPaymentAmount = BigDecimal.ZERO;
            return;
        }

        totalPaymentAmount = BigDecimal.ZERO;

        for (ApartmentCharge charge : apartmentChargeList) {
            if (charge.getChargeAmount() != null) {
                totalPaymentAmount = totalPaymentAmount.add(charge.getChargeAmount());
            }
        }
    }
    // tinh totalAmountDue
    public void calculateTotalAmountDue() {
        if (totalPaymentAmount == null) {
            totalPaymentAmount = BigDecimal.ZERO;
        }

        if (totalAmountPaid == null) {
            totalAmountPaid = BigDecimal.ZERO;
        }

        totalAmountDue = totalPaymentAmount.subtract(totalAmountPaid);
    }



    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        totalPayment();
        calculateTotalAmountDue();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
        totalPayment();
        calculateTotalAmountDue();
    }
}
