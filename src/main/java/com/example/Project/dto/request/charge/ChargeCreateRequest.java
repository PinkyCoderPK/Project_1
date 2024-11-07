package com.example.Project.dto.request.charge;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargeCreateRequest {
    @NotNull(message = "Tên phí không được để trống")
    String chargesName;

    @NotNull(message = "Yêu cầu nộp không được để trống")
    Boolean isMandatory; // Có bắt buộc không

    @NotNull(message = "Đơn giá không được để trống")
    BigDecimal unitAmount; // Phí/Đơn vị

    @NotNull(message = "Đơn vị tính không được để trống")
    String unitMeasurement; // Đơn vị đo lường

}
