package com.example.Project.dto.request.resident;

import com.example.Project.enums.Enums;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentSearchRequest {
    String apartmentId;

    String residentName;

    Enums.ResidentRole role;

    String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    String permanentAddress;

    String temporaryAddress;
}
