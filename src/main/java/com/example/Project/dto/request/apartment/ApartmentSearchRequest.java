package com.example.Project.dto.request.apartment;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ApartmentSearchRequest {

    private String apartmentName;
    private Integer floorNumber;
    private Integer apartmentNumber;

    @PositiveOrZero(message = "Diện tích phải là số không âm")
    private Double area;

    private String ownerId;
    private String status;
    private LocalDateTime createdAt;

}
