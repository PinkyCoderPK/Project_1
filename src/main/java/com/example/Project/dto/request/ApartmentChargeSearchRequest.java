package com.example.Project.dto.request;

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

    @PositiveOrZero(message = "Đơn giá phải là số không âm")
    Double unitAmount;

    String unitMeasurement;

    @PositiveOrZero(message = "Số lượng phải là số không âm")
    Double unitQuantity;

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    Double amountPaid;

    @PositiveOrZero(message = "Số tiền còn thiếu phải là số không âm")
    Double amountDue;

    LocalDateTime chargeDate;

    LocalDateTime dueDate;

    String status;

    String paymentMethod;
}
