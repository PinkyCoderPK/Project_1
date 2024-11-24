package com.example.Project.dto.request.bill;

import com.example.Project.dto.request.apartmentCharge.ApartmentChargeCreateRequest;
import com.example.Project.enums.Enums;
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

    @NotNull(message = "Id phòng không được để trống")
    String apartmentId;

    List<ApartmentChargeCreateRequest> apartmentChargeCreateRequestList;

    @Builder.Default
    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal totalAmountPaid = BigDecimal.ZERO;

    @NotNull(message = "Tháng thu phí không được để trống")
    LocalDateTime month;

    @Builder.Default
    Enums.BillStatus status = Enums.BillStatus.UNPAID; // Còn thiếu / Trả đủ

    Enums.PaymentMethod paymentMethod;
}
