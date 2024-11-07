package com.project.ABM.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentCreationRequest {

    @NotNull(message = "NULL_APARTMENTID")
    String apartmentId;
    @NotNull(message = "NULL_USERNAME")
    @Size (min = 3, max = 24, message = "USERNAME_INVALID")
    String username;
    @NotNull
    Boolean role;
    @Pattern(regexp = "^[0-9]{10}$", message = "PHONENUMBER_INVALID")
    String phoneNumber;
    @NotNull(message = "NULL_BIRTHDAY")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;
    String permanentAddress;
    String temporaryAddress;

    public LocalDate getCreateAt() {
        return java.time.LocalDate.now();
    }

}
