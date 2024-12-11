package com.example.Project.dto.request.bill;

import com.example.Project.enums.Enums;
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

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal totalAmountPaid;
    LocalDateTime monthYear;
    Enums.BillStatus status;
    Enums.PaymentMethod paymentMethod;
}
