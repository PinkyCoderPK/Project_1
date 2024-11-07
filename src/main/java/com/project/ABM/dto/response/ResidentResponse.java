package com.project.ABM.dto.response;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentResponse {
    String residentId;

    String apartmentId;
    String username;
    Boolean role;
    String phoneNumber;
    LocalDate birthday;
    String permanentAddress;
    String temporaryAddress;
    LocalDate createAt;
    LocalDate updateAt;
}
