package com.example.Project.dto.request.apartmentCharge;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentChargeCreateRequest {
    @NotNull(message = "Id phòng không được để trống")
    String apartmentId;

    @NotNull(message = "Id phí không được để trống")
    String chargeId;

    @NotNull(message = "Đơn giá không được để trống")
    @PositiveOrZero(message = "Đơn giá phải là số không âm")
    Double unitAmount;

    @NotNull(message = "Đơn vị tính không được để trống")
    String unitMeasurement;

    @NotNull(message = "Số lượng không được để trống")
    @PositiveOrZero(message = "Số lượng phải là số không âm")
    Double unitQuantity;

    @NotNull(message = "Số tiền đã thanh toán không được để trống")
    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    Double amountPaid;

    @NotNull(message = "Số tiền còn thiếu không được để trống")
    @PositiveOrZero(message = "Số tiền còn thiếu phải là số không âm")
    Double amountDue;

    @NotNull(message = "Ngày bắt đầu thu phí không được để trống")
    LocalDateTime chargeDate;

    @NotNull(message = "Hạn thu phí không được để trống")
    LocalDateTime dueDate;

    @NotNull(message = "Trạng thái thu phí không được để trống")
    String status;

    String paymentMethod;
}
