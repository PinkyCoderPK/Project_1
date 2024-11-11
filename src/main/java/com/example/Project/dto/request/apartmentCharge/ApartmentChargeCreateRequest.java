package com.example.Project.dto.request.apartmentCharge;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @PositiveOrZero(message = "Đơn giá phải là số không âm")
    Double unitAmount;

    String unitMeasurement;

    @Builder.Default
    @PositiveOrZero(message = "Số lượng phải là số không âm")
    Double unitQuantity = (double) 0;

    @Builder.Default
    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    Double amountPaid = (double) 0;

    @Builder.Default
    @PositiveOrZero(message = "Số tiền còn thiếu phải là số không âm")
    Double amountDue = (double) 0;

    @NotNull(message = "Ngày bắt đầu thu phí không được để trống")
    LocalDateTime chargeDate;

    @NotNull(message = "Hạn thu phí không được để trống")
    LocalDateTime dueDate;

    String paymentMethod;
}
