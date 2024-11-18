package com.example.Project.dto.request.resident;

import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResidentSearchRequest {
    String apartmentId;
    String username;
    Boolean role;
    String phoneNumber;
    @Past(message = "Sinh nhật phải trước thời điểm hiện tại")
    LocalDate birthday;
    String permanentAddress;
    String temporaryAddress;
}
