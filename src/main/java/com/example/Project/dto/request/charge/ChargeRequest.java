package com.example.Project.dto.request.charge;

import com.example.Project.enums.Enums;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargeRequest {
    @NotNull(message = "Tên phí không được để trống")
    String chargeName;

    @NotNull(message = "Loại phí không được để trống")
    Enums.ChargeType type;

    String description;

    @NotNull(message = "Đon giá phí không được để trống")
    Double unitAmount; // Phí/Đơn vị

    @NotNull(message = "Đon vị phí không được để trống")
    String unitMeasurement; // Đơn vị đo lường

    @Builder.Default
    LocalDateTime chargeDate = LocalDateTime.now();

    LocalDateTime dueDate;
}
