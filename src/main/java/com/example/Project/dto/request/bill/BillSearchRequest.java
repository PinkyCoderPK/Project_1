package com.example.Project.dto.request.bill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillSearchRequest {
    List<String> apartmentChargeIds;

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal totalAmountPaid;

    LocalDateTime month;
    String status; // Còn thiếu / Trả đủ
    String paymentMethod;
}
