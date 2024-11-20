package com.example.Project.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeResponse {
    String apartmentId;
    String apartmentName;
    String chargeId;
    String chargeName;
    BigDecimal chargeAmount;
    BigDecimal unitQuantity;
    BigDecimal amountPaid;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String paymentMethod;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
