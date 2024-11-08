package com.example.Project.dto.request.charge;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargeSearchRequest {

    @NotNull(message = "Tên phí không được để trống")
    String chargesName;

    Boolean isMandatory; // Có bắt buộc không
    String description;
    BigDecimal unitAmount; // Phí/Đơn vị
    String unitMeasurement; // Đơn vị đo lường
}
