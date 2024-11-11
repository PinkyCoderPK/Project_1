package com.example.Project.dto.request.charge;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargeSearchRequest {

    @NotNull(message = "Tên phí không được để trống")
    String chargeName;

    Boolean isMandatory; // Có bắt buộc không
    String description;
    Double unitAmount; // Phí/Đơn vị
    String unitMeasurement; // Đơn vị đo lường
}
