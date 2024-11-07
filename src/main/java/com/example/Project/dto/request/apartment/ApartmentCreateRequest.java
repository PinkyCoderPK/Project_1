package com.example.Project.dto.request.apartment;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class ApartmentCreateRequest {
    @NotNull(message = "Id căn hộ không được để trống")
    private String id;

    @NotNull(message = "Tên căn hộ không được để trống")
    private String apartmentName;

    @NotNull(message = "Số tầng không được để trống")
    private Integer floorNumber;

    @NotNull(message = "Mã số căn hộ không được để trống")
    private Integer apartmentNumber;

    @NotNull(message = "Diện tích căn hộ không được để trống")
    private BigDecimal area;

    @NotNull(message = "Id người sở hữu không được để trống")
    private String ownerId;

    @NotNull(message = "Trạng thái căn hộ không được để trống")
    private String status;
}
