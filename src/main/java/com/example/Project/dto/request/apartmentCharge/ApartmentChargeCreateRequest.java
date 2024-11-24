package com.example.Project.dto.request.apartmentCharge;

import com.example.Project.enums.Enums;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    String apartmentId;

    @NotNull(message = "Id phí không được để trống")
    String chargeId;

    @Builder.Default
    @PositiveOrZero(message = "Số lượng phải là số không âm")
    BigDecimal unitQuantity =  BigDecimal.ZERO;;

    @Builder.Default
    @PositiveOrZero(message = "Số tiền đã thanh toán phải là số không âm")
    BigDecimal amountPaid =  BigDecimal.ZERO;;

    Enums.PaymentMethod paymentMethod;
}
