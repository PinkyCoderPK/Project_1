package com.example.Project.dto.request.apartmentCharge;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeSearchRequest {

    String apartmentId;

    String chargeId;

    @PositiveOrZero(message = "Số lượng phải là số không âm")
    Double unitQuantity;

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    Double amountPaid;

    LocalDateTime chargeDate;

    LocalDateTime dueDate;

    String paymentMethod;
}
