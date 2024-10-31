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
    Double unitAmount;
    String unitMeasurement;
    Double unitQuantity;
    Double amountPaid;
    Double amountDue;
    LocalDateTime chargeDate;
    LocalDateTime dueDate;
    String status;
    String paymentMethod;
}
