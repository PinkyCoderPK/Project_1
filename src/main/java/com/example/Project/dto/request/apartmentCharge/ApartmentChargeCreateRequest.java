package com.example.Project.dto.request.apartmentCharge;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeCreateRequest {
    @NotNull(message = "Id phòng không được để trống")
    String apartmentId;

    @NotNull(message = "Id phí không được để trống")
    String chargeId;

    @Builder.Default
    @PositiveOrZero(message = "Số lượng phải là số không âm")
    BigDecimal unitQuantity =  BigDecimal.ZERO;;

    @Builder.Default
    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal amountPaid =  BigDecimal.ZERO;;

    @NotNull(message = "Ngày bắt đầu thu phí không được để trống")
    LocalDateTime chargeDate;

    @NotNull(message = "Hạn thu phí không được để trống")
    LocalDateTime dueDate;

    String paymentMethod;
}
