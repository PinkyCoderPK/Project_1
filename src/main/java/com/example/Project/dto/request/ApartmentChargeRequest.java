package com.example.Project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeRequest {
    String apartmentId;
    String chargeId;
    double unitAmount;
    String unitMeasurement;
    double unitQuantity;
    double amountPaid;
    double amountDue;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String status;
    String paymentMethod;
}
