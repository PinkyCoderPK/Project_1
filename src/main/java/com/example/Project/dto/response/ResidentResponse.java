package com.example.Project.dto.response;

import com.example.Project.enums.Enums;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentResponse {
    String id;
    String apartmentId;
    String apartmentName;
    String username;
    Enums.ResidentRole role;
    String phoneNumber;
    LocalDate birthday;
    String permanentAddress;
    String temporaryAddress;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
