package com.example.Project.dto.request.bill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillCreateRequest {

    @NotNull(message = "Danh sách ApartmentCharge không được để trống")
    List<String> apartmentChargeIds;

    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal totalAmountPaid;

    @NotNull(message = "Tháng thu phí không được để trống")
    LocalDateTime month;

    @NotNull(message = "Tình trạng thu phí không được để trống")
    String status; // Còn thiếu / Trả đủ

    @NotNull(message = "Phương thức thanh toán không được để trống")
    String paymentMethod;
}
