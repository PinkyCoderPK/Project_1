package com.project.ABM.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
public class ResidentUpdateRequest {
    String apartmentId;
    Boolean role;

    @Pattern(regexp = "^[0-9]{10}$", message = "PHONENUMBER_INVALID")
    String phoneNumber;
    LocalDate birthday;
    String temporaryAddress;

    public LocalDate getUpdateAt() {
        return java.time.LocalDate.now();
    }

}
