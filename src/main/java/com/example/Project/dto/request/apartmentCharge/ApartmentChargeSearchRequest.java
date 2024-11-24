package com.example.Project.dto.request.apartmentCharge;

import com.example.Project.enums.Enums;
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

    Double chargeAmount;

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    Double amountPaid;

    Enums.PaymentMethod paymentMethod;
}
