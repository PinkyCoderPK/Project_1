package com.example.Project.dto.request.charge;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargeCreateRequest {
    @NotNull(message = "Tên phí không được để trống")
    String chargeName;

    @NotNull(message = "Yêu cầu nộp không được để trống")
    Boolean isMandatory; // Có bắt buộc không

    String description;

    Double unitQuantity; // số lượng

    Double unitAmount; // Phí/Đơn vị

    String unitMeasurement; // Đơn vị đo lường

}
