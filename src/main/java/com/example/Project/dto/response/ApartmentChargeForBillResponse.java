package com.example.Project.dto.response;

import com.example.Project.enums.Enums;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeForBillResponse {
    String chargeId;
    String chargeName;
    BigDecimal unitQuantity;
    BigDecimal chargeAmount;
}
